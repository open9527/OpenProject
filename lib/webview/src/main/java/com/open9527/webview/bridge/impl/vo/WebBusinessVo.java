package com.open9527.webview.bridge.impl.vo;

public class WebBusinessVo extends WebBaseVo {
  private String id; //业务内容id
    private String contentType;               //业务内容类型：content, subject，具体由业务方自己定义
    //业务URL, 复制链接功能使用该数据
    private String link;
    //设置完成后，是否刷新相关页面元素。shortcurts、panels、comments、tick、footer等
   private boolean refresh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }
}
