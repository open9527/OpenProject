package com.open9527.webview.bridge.impl.vo;

import java.io.Serializable;

/**
 * 当前登录用户信息
 */
public class WebUserInfoQueryParam implements Serializable {

    private String token; //会话token
    private AccountBean account;

    public WebUserInfoQueryParam(String token, AccountBean account) {
        this.token = token;
        this.account = account;
    }

    public static class AccountBean implements Serializable {

        private String id; //用户id
        private String nickname; //昵称
        private AvatarBean avatar; //昵称

        public AccountBean(String id, String nickname, AvatarBean avatar) {
            this.id = id;
            this.nickname = nickname;
            this.avatar = avatar;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public AvatarBean getAvatar() {
            return avatar;
        }

        public void setAvatar(AvatarBean avatar) {
            this.avatar = avatar;
        }
    }

    public static class AvatarBean implements Serializable {
        private String sourceUrl;//头像原图地址
        private String thumbUrl;//头像缩略图地址

        public AvatarBean(String sourceUrl, String thumbUrl) {
            this.sourceUrl = sourceUrl;
            this.thumbUrl = thumbUrl;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }

        public String getThumbUrl() {
            return thumbUrl;
        }

        public void setThumbUrl(String thumbUrl) {
            this.thumbUrl = thumbUrl;
        }
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AccountBean getAccount() {
        return account;
    }

    public void setAccount(AccountBean account) {
        this.account = account;
    }


    public static WebUserInfoQueryParam getWebUserInfo(String account) {
        return new WebUserInfoQueryParam(account, new AccountBean(account, account, new AvatarBean(account, account)));
    }
}
