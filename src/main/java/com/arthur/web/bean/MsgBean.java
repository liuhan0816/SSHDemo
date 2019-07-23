package com.arthur.web.bean;

/**
 * @ClassName MsgBean
 * @Description TODO
 * @Author liuhan
 * @Date 2019/7/2 16:31
 * @Version 1.0
 **/
public class MsgBean implements java.io.Serializable {
    /** 标题*/
    private String	title;
    /** 标志F否 T是 **/
    private String	flag;
    /** 内容*/
    private String	content;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
