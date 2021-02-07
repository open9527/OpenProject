package com.open9527.wanandroid.pkg.net;

import java.io.Serializable;

/**
 * @author open_9527
 * Create at 2021/1/14
 **/
public class ContentVo implements Serializable {
    
    private String title;
    private String link;
    private String author;
    private String shareUser;
    private String niceShareDate;
    private String envelopePic;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getShareUser() {
        return shareUser;
    }

    public void setShareUser(String shareUser) {
        this.shareUser = shareUser;
    }

    public String getNiceShareDate() {
        return niceShareDate;
    }

    public void setNiceShareDate(String niceShareDate) {
        this.niceShareDate = niceShareDate;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    @Override
    public String toString() {
        return "ContentVo{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", author='" + author + '\'' +
                ", shareUser='" + shareUser + '\'' +
                ", niceShareDate='" + niceShareDate + '\'' +
                ", envelopePic='" + envelopePic + '\'' +
                '}';
    }
}
