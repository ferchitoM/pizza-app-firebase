package com.example.pizza_app_firebase.view;

import com.example.pizza_app_firebase.cart.CartProduct;

import java.util.ArrayList;

public interface CartView {
    void saveShoppingCart(ArrayList<CartProduct> products);
    void showMessageError(String errorMessage);
    void showRemoveProductDialog(CartProduct product, int index);
    void buttonPayment(boolean paymentVisibility);
}
