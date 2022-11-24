package com.sae.tukangin.utils;

import java.time.LocalDate;

public class ChatData {
    private String sender;
    private String[] message;
    private LocalDate date;
    private String image;

    public ChatData(String sender, String[]  message, LocalDate date) {
        this.sender = sender;
        this.message = message;
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String[] getMessage() {
        return message;
    }

    public void setMessage(String[] message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLastMessage() {
        return message[message.length - 1];
    }
}
