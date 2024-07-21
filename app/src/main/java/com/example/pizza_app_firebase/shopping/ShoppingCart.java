package com.example.pizza_app_firebase.shopping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ShoppingCart {

    public String dateTime;
    public ArrayList<CartProduct> products;
    public Long subtotal;
    public Long iva;
    public Long total;

    public ShoppingCart() {
        this.dateTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        this.products = new ArrayList<CartProduct>();
        this.subtotal = 0L;
        this.iva = 0L;
        this.total = 0L;
    }

    public ShoppingCart(String dateTime, ArrayList<CartProduct> products, Long subtotal, Long iva, Long total) {
        this.dateTime = dateTime;
        this.products = products;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
    }

    public void addProduct(CartProduct product){
        this.products.add(product);
    }

    public void removeProduct(Long index){
        this.products.remove(index);
    }

    public Long newId(){
        return Long.valueOf(products.size());
    }
}
