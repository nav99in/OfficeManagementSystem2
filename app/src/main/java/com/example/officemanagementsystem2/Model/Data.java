package com.example.officemanagementsystem2.Model;


public class Data {
    String title, note,date,id;

    public Data() {

    }
    public Data(String s_title, String s_notes, String s_date, String s_id) {
        this.title = title;
        this.note = note;
        this.date = date;
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
