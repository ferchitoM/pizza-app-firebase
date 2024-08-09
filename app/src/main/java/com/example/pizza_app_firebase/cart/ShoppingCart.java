package com.example.pizza_app_firebase.cart;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ShoppingCart {

    public ArrayList<CartProduct>   products;
    public LocalDateTime            dateTime;
    public String                   commentary;
    public Long                     iva;
    public Long                     subtotal;
    public Long                     total;
    public Long                     otherExpenses;
    public Long                     discount;
    public Long                     havePaid;
    public Long                     change;
    public String                   paymentMethod;
    public Long                     totalPayment;
    public Delivery                 delivery;

    public ShoppingCart() {
        //this.dateTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        this.dateTime = LocalDateTime.now();
        this.products = new ArrayList<CartProduct>();
        this.commentary = "";
        this.iva = 0L;
        this.subtotal = 0L;
        this.total = 0L;
        this.otherExpenses = 0L;
        this.discount = 0L;
        this.havePaid = 0L;
        this.change = 0L;
        this.paymentMethod = "";
        this.totalPayment = 0L;
        this.delivery = new Delivery();

    }

    public ShoppingCart(ArrayList<CartProduct> products, LocalDateTime dateTime, String commentary, Long iva, Long subtotal, Long total, Long otherExpenses, Long discount, Long havePaid, Long change, String paymentMethod, Long totalPayment, Delivery delivery) {
        this.products = products;
        this.dateTime = dateTime;
        this.commentary = commentary;
        this.iva = iva;
        this.subtotal = subtotal;
        this.total = total;
        this.otherExpenses = otherExpenses;
        this.discount = discount;
        this.havePaid = havePaid;
        this.change = change;
        this.paymentMethod = paymentMethod;
        this.totalPayment = totalPayment;
        this.delivery = delivery;
    }

    public void addProduct(CartProduct product){
        this.products.add(product);
    }

    public void removeProduct(int index){ this.products.remove(index); }

    public Long newId(){
        return Long.valueOf(products.size());
    }

    public int getSize(){ return products.size(); }
}
