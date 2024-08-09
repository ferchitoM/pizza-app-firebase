package com.example.pizza_app_firebase.view;

import com.example.pizza_app_firebase.product.Product;

import java.util.ArrayList;
import java.util.Map;

public interface DrinksView {
    void showProductsOnList(ArrayList<Product> products);
    void showMessageError(String errorMessage);
    void loadProducts();
    void showDialog(Product product, int index);
}
