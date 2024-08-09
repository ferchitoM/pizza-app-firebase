package com.example.pizza_app_firebase.cart;

import com.google.type.DateTime;

public class Delivery {
    public String address;
    public Long phone;
    public DateTime deliveryDate;

    public Delivery() {
        this.address = "";
        this.phone = 0L;
        this.deliveryDate = null;
    }

    public Delivery(String address, Long phone, DateTime deliveryDate) {
        this.address = address;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
    }
}
