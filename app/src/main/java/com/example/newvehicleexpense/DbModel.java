package com.example.newvehicleexpense;

public class DbModel {

    String id,itemName,note,date,ammount,type;

    public DbModel() {
    }

    public DbModel(String id, String itemName, String note, String date, String ammount, String type) {
        this.id = id;
        this.itemName = itemName;
        this.note = note;
        this.date = date;
        this.ammount = ammount;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
