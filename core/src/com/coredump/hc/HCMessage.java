package com.coredump.hc;

/**
 * Created by Gregory on 8/12/2016.
 */
public class HCMessage {

    private String subject;
    private String body;


    public HCMessage(String subject, String body){
        this.subject = subject;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }

}
