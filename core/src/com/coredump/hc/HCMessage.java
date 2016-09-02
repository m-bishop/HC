package com.coredump.hc;

/**
 * Created by Gregory on 8/12/2016.
 */
public class HCMessage {

    private String subject;
    private String body;
    private int msgID;

    public HCMessage(String subject, String body, int msgID){
        this.subject = subject;
        this.body = body;
        this.msgID = msgID;
    }

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }

    public int getMsgID() {
        return msgID;
    }
}
