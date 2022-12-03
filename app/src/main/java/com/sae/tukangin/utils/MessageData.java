package com.sae.tukangin.utils;

public class MessageData {
    private String is_user;
    private String user_id;
    private String tukang_id;
    private String message;

    public MessageData(String user_id, String tukang_id, String message) {
        this.user_id = user_id;
        this.tukang_id = tukang_id;
        this.message = message;
    }

    public MessageData(String message, String is_user) {
        this.message = message;
        this.is_user = is_user;
    }

    public String getIs_user() {
        return is_user;
    }

    public void setIs_user(String is_user) {
        this.is_user = is_user;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTukang_id() {
        return tukang_id;
    }

    public void setTukang_id(String tukang_id) {
        this.tukang_id = tukang_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
