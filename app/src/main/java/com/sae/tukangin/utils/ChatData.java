package com.sae.tukangin.utils;

import java.time.LocalDate;

public class ChatData {
    private String sender;
    private String[] message;
    private LocalDate date;
    private String image;
    private String tukang_name;
    private String layanan_name;
    private int imgId;
    private String id_chat;
    private String tukang_id;


    public ChatData(String sender, String[] message, LocalDate date) {
        this.sender = sender;
        this.message = message;
        this.date = date;
    }

    public ChatData(String tukang_name, String layanan_name, Integer imgId, Integer id_chat, String tukang_id) {
        this.tukang_name = tukang_name;
        this.layanan_name = layanan_name;
        this.imgId = imgId;
        this.tukang_id = tukang_id;
    }

    public String getTukang_id() {
        return tukang_id;
    }

    public void setTukang_id(String tukang_id) {
        this.tukang_id = tukang_id;
    }

    public String getId_chat() {
        return id_chat;
    }

    public void setId_chat(String id_chat) {
        this.id_chat = id_chat;
    }

    public String getTukang_name() {
        return tukang_name;
    }

    public void setTukang_name(String tukang_name) {
        this.tukang_name = tukang_name;
    }

    public String getLayanan_name() {
        return layanan_name;
    }

    public void setLayanan_name(String layanan_name) {
        this.layanan_name = layanan_name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
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
