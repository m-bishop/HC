package com.coredump.hc;

/**
 * Created by Gregory on 8/12/2016.
 */
public class HCMessage {

    private String subject;
    private String body;
    private String avatar;


    public HCMessage(String subject, String body, String avatar){
        this.subject = subject;
        this.body = body;
        this.avatar = avatar;
    }

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }

    public String getAvatar() { return avatar; }

}
