package com.example.hydrateme.objects;

public class WaterLog {
    String time;
    String quantity;


    public WaterLog(String time, String quantity) {
        this.time = time;
        this.quantity = quantity;
    }

    public String getTime() {
        return time;
    }

    public String getQuantity() {
        return quantity;
    }
}
