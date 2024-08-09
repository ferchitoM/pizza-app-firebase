package com.example.pizza_app_firebase.cart;

public class CartProduct {

    public Long id;
    public String name;
    public String image;
    public String size;
    public Long price;
    public Long amount; //cantidad
    public Long subtotal;
    public Long iva;
    public Long total;

    public CartProduct() {
        this.id = 0L;
        this.name = "";
        this.image = "";
        this.size = "";
        this.price = 0L;
        this.amount = 0L;
        this.subtotal = 0L;
        this.iva = 0L;
        this.total = 0L;
    }

    public CartProduct(Long id, String name, String image, String size, Long price, Long amount, Long subtotal, Long iva, Long total) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.size = size;
        this.price = price;
        this.amount = amount;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
    }

    public void calculateValues(){
        total      = price * amount;
        iva        = Double.valueOf(Math.round(total - (subtotal / 1.19))).longValue();
        subtotal   = total - iva;
    }
}