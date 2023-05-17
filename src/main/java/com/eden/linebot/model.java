package com.eden.linebot;

import org.springframework.stereotype.Component;

@Component
public class model {
    private String user_id;
    private String msg_history;


    public String getUser_id() {
        return user_id;
    }

    public String getMsg_history() {
        return msg_history;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setMsg_history(String msg_history) {
        this.msg_history = msg_history;
    }



}
