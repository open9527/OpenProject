package com.open9527.webview.bridge.impl.vo;

import java.io.Serializable;

public class WebThemeQueryParam implements Serializable {
    private FontSizeBean fontSize;
    private ColorBean color;

    public FontSizeBean getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSizeBean fontSize) {
        this.fontSize = fontSize;
    }

    public ColorBean getColor() {
        return color;
    }

    public void setColor(ColorBean color) {
        this.color = color;
    }

    //可选字体方案设置，当选择字体方案时，通过事件 rmt.fontChanged 传递给页面
    public static class FontSizeBean {
        private String id;   //字体方案编号
        private String name;     //字体名称
        private String size;    //字体标号

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }
    }

     //可选颜色方案设置，当选择颜色方案时，通过事件 rmt.colorChanged 传递给页面
    public static class ColorBean {
      private String id;          //颜色方案标号
      private String name;        //方案名称
      private String imageUrl; //方案样本图

         public String getId() {
             return id;
         }

         public void setId(String id) {
             this.id = id;
         }

         public String getName() {
             return name;
         }

         public void setName(String name) {
             this.name = name;
         }

         public String getImageUrl() {
             return imageUrl;
         }

         public void setImageUrl(String imageUrl) {
             this.imageUrl = imageUrl;
         }
     }
}
