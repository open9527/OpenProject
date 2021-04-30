package com.open9527.webview.bridge.impl.vo;


public class WebSchemaVo extends WebBaseVo {
    //valid: true,    //支持:true，不支持:false
    private boolean valid;
    private String schema;


    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
