#Router 
#1.使用方法 
  #初始化      Application中  Router.init(this);
  #配置  buildLib中 route 获取 moduleName
               javaCompileOptions { annotationProcessorOptions { includeCompileClasspath = true } }
               javaCompileOptions {
                   annotationProcessorOptions {
                       arguments = [moduleName: project.getName()]
                   }
               }
  
  #创建路由Path   
              eg: String PATH_WEBVIEW_WEBVIEWACTIVITY = "/webview/WebViewActivity";
  #调用方法 
              eg: 
                Bundle bundle = BundleUtils.create(new WebViewActivity.BundleData("https://www.wanandroid.com/index", "wanandroid"));
              Router.getsInstance()
                                   .build(Path.PATH_WEBVIEW_WEBVIEWACTIVITY)
                                   //参数传递
                                   .withBundle(bundle)
                                   .navigation(LauncherActivity.this, new NavigationCallback() {
                                       @Override
                                       public void onFound(Postcard postcard) {
                                           LogUtils.i(TAG, "NavigationCallback" + "找到跳转页面");
           
                                       }
           
                                       @Override
                                       public void onLost(Postcard postcard) {
                                           LogUtils.i(TAG, "NavigationCallback" + "未找到");
                                       }
           
                                       @Override
                                       public void onArrival(Postcard postcard) {
                                           LogUtils.i(TAG, "NavigationCallback" + "成功跳转");
                                       }
                                   });

  #接收页面调用方法 
                 eg: WebViewActivity 添加注解 @Router(path = PATH_WEBVIEW_WEBVIEWACTIVITY)
                   接收参数 BundleData bundleData = BundleUtils.getBundleData(bundle, BundleData.class);
                   
  


1.生成路径 ...generated/source/kapt/debug/com/open9527/routes/

   
   
 
