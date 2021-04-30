//notation: js file can only use this kind of comments
//since comments will cause error when use in webview.loadurl,
//comments will be remove by java use regexp
(function () {
    if (window.WebViewJavascriptBridge) {
        return;
    }
    var messagingIframe;
    var bizMessagingIframe;
    var sendMessageQueue = [];
    var receiveMessageQueue = [];
    var messageHandlers = {};

    var RMT_SCHEME = 'rmt';
    var QUEUE_HAS_MESSAGE = '__QUEUE_MESSAGE__/';

    var responseCallbacks = {};
    var uniqueId = 1;

    // 创建消息index队列iframe
    function _createQueueReadyIframe(doc) {
        messagingIframe = doc.createElement('iframe');
        messagingIframe.style.display = 'none';
        doc.documentElement.appendChild(messagingIframe);
    }

    //创建消息体队列iframe
    function _createQueueReadyIframe4biz(doc) {
        bizMessagingIframe = doc.createElement('iframe');
        bizMessagingIframe.style.display = 'none';
        doc.documentElement.appendChild(bizMessagingIframe);
    }

    //set default messageHandler  初始化默认的消息线程
    function init(messageHandler) {
        if (WebViewJavascriptBridge._messageHandler) {
            throw new Error('WebViewJavascriptBridge.init called twice');
        }
        WebViewJavascriptBridge._messageHandler = messageHandler;
        var receivedMessages = receiveMessageQueue;
        receiveMessageQueue = null;
        for (var i = 0; i < receivedMessages.length; i++) {
            _dispatchMessageFromNative(receivedMessages[i]);
        }
    }

    // 发送
    function send(data, responseCallback) {
        _doSend({
            data: data
        }, responseCallback);
    }

    // 注册线程 往数组里面添加值
    function registerHandler(handlerName, handler) {
        messageHandlers[handlerName] = handler;
    }

    // 调用线程
    function callHandler(handlerName, data, responseCallback) {
        _doSend({
            handlerName: handlerName,
            data: data
        }, responseCallback);
    }

    //sendMessage add message, 触发native处理 sendMessage
    function _doSend(message, responseCallback) {
        if (responseCallback) {
            var callbackId = 'cb_' + (uniqueId++) + '_' + new Date().getTime();
            responseCallbacks[callbackId] = responseCallback;
            message.callbackId = callbackId;
        }

        sendMessageQueue.push(message);
        messagingIframe.src = RMT_SCHEME + '://' + QUEUE_HAS_MESSAGE;
    }

    // 提供给native调用,该函数作用:获取sendMessageQueue返回给native,由于android不能直接获取返回的内容,所以使用url shouldOverrideUrlLoading 的方式返回内容
    function _fetchQueue() {
        var messageQueueString = JSON.stringify(sendMessageQueue);
        sendMessageQueue = [];
        //android can't read directly the return data, so we can reload iframe src to communicate with java
        if (messageQueueString !== '[]') {
            bizMessagingIframe.src = RMT_SCHEME + '://return/_fetchQueue/' + encodeURIComponent(messageQueueString);
        }
    }

    //提供给native使用,
    function _dispatchMessageFromNative(messageJSON) {
        setTimeout(function () {
            var message = JSON.parse(messageJSON);
            var responseCallback;
            //java call finished, now need to call js callback function
            if (message.responseId) {
                responseCallback = responseCallbacks[message.responseId];
                if (!responseCallback) {
                    return;
                }
                responseCallback(message.responseData);
                delete responseCallbacks[message.responseId];
            } else {
                //直接发送
                if (message.callbackId) {
                    var callbackResponseId = message.callbackId;
                    responseCallback = function (responseData) {
                        _doSend({
                            responseId: callbackResponseId,
                            responseData: responseData
                        });
                    };
                }

                var handler = WebViewJavascriptBridge._messageHandler;
                if (message.handlerName) {
                    handler = messageHandlers[message.handlerName];
                }

                //查找指定handler
                try {
                    handler(message.data, responseCallback);
                } catch (exception) {
                    if (typeof console != 'undefined') {
                        console.log("WebViewJavascriptBridge: WARNING: javascript handler threw.", message, exception);
                    }
                }
            }
        });
    }

    //提供给native调用,receiveMessageQueue 在会在页面加载完后赋值为null,所以
    function _handleMessageFromNative(messageJSON) {
        if (receiveMessageQueue) {
            receiveMessageQueue.push(messageJSON);
        }
        _dispatchMessageFromNative(messageJSON);

    }

    var WebViewJavascriptBridge = window.WebViewJavascriptBridge = {
        init: init,
        send: send,
        registerHandler: registerHandler,
        callHandler: callHandler,
        _fetchQueue: _fetchQueue,
        _handleMessageFromNative: _handleMessageFromNative
    };

    var doc = document;
    _createQueueReadyIframe(doc);
    _createQueueReadyIframe4biz(doc);
    var readyEvent = doc.createEvent('Events');
    readyEvent.initEvent('WebViewJavascriptBridgeReady');
    readyEvent.bridge = WebViewJavascriptBridge;
    doc.dispatchEvent(readyEvent);
})();


(function () {
    if (window.rmt) {
        return;
    }

    var _registerHandler = function (target, handlerName) {
        if (typeof target === 'undefined' || typeof target[handlerName] !== 'undefined') {
            return;
        }

        target[handlerName] = function (request) {
            window.WebViewJavascriptBridge.callHandler(handlerName, request, function (responseString) {
                if (typeof request === 'undefined' || typeof responseString === 'undefined') {
                    return;
                }
                try {
                    var response = JSON.parse(responseString);
                    if (response.type === 'success') {
                        if (typeof request.success === 'function') {
                            request.success.call(this, response.data);
                        }

                    } else if (response.type === 'cancel') {
                        if (typeof request.cancel === 'function') {
                            request.cancel.call(this, response.data);
                        }

                    } else if (response.type === 'fail') {
                        if (typeof request.fail === 'function') {
                            request.fail.call(this, response.data);
                        }

                    } else {
                        if (typeof request.fail === 'function') {
                            request.fail.call(this, {
                                code: 'error',
                                message: 'bad response'
                            });
                        }
                    }

                } catch (exp) {
                    if (typeof request.fail === 'function') {
                        request.fail.call(this, {
                            code: 'error',
                            message: exp.toString()
                        });
                    }
                }

                if (typeof request.complete === 'function') {
                    request.complete.call(this);
                }
            });
        }
    }

    var _eventHandler = {};
    var _registerEvent = function (target, handlerName) {
        if (typeof target === 'undefined' || typeof _eventHandler[handlerName] !== 'undefined') {
            return;
        }

        target[handlerName] = function (callback) {
            _eventHandler[handlerName] = callback;
            return target;
        };

        window.WebViewJavascriptBridge.registerHandler(handlerName, function (responseString) {
            var response;
            if (typeof responseString === 'string' && responseString.length > 0) {
                response = JSON.parse(responseString);
            }

            if (typeof _eventHandler[handlerName] === 'function') {
                _eventHandler[handlerName].call(this, response);
            }

        });
    };

    var rmt = window.rmt = {
        registerHandler: function (handlerName) {
            _registerHandler(this, handlerName);
        },
        registerEvent: function (handlerName) {
            _registerEvent(this, handlerName);
        }
    };

    rmt.registerHandler('getVersion');
    rmt.registerHandler('getToken');
    rmt.registerHandler('getAccount');

    rmt.registerHandler('configUI');
    rmt.registerHandler('configShare');

    rmt.registerHandler('getNetworkType');
    rmt.registerHandler('getLocation');
    rmt.registerHandler('setClipboardText');
    rmt.registerHandler('getClipboardText');

    rmt.registerHandler('showHeader');
    rmt.registerHandler('hideHeader');
    rmt.registerHandler('showShortcuts');
    rmt.registerHandler('hideShortcuts');
    rmt.registerHandler('showPanels');
    rmt.registerHandler('hidePanels');
    rmt.registerHandler('showComments');
    rmt.registerHandler('hideComments');
    rmt.registerHandler('showFooter');
    rmt.registerHandler('hideFooter');
    rmt.registerHandler('showTick');
    rmt.registerHandler('hideTick');
    rmt.registerHandler('updateWebViewHeight');

    rmt.registerHandler('getBusiness');
    rmt.registerHandler('updateBusiness');

    rmt.registerHandler('openAction');
    rmt.registerHandler('openLogin');
    rmt.registerHandler('openAlbum');
    rmt.registerHandler('openComment');
    rmt.registerHandler('openReport');
    rmt.registerHandler('openMoreBox');
    rmt.registerHandler('openVideoPlayer');

    rmt.registerHandler('downloadImage');
    rmt.registerHandler('scanImage');
    rmt.registerHandler('showPicEdit');

    rmt.registerHandler('getSubscription');
    rmt.registerHandler('followSubscription');
 //   rmt.registerHandler('getPhoneAndVideo');
    rmt.registerHandler('unfollowSubscription');

    rmt.registerEvent('voiceClick');
    rmt.registerEvent('longTouch');
    rmt.registerEvent('themeChange');

})();
