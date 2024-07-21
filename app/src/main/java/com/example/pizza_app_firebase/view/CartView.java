package com.example.pizza_app_firebase.view;

import com.example.pizza_app_firebase.shopping.CartProduct;

import java.util.ArrayList;

public interface CartView {
    void saveShoppingCart(ArrayList<CartProduct> products);
    void showMessageError(String errorMessage);
    void showRemoveProductDialog(CartProduct product);
}
