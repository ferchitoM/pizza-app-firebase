package com.example.pizza_app_firebase.presenter;

import com.example.pizza_app_firebase.model.PizzasModel;
import com.example.pizza_app_firebase.product.Product;
import com.example.pizza_app_firebase.view.PizzasView;

import java.util.ArrayList;
import java.util.Map;

public class PizzasPresenter {

    private PizzasModel model;
    private PizzasView view;

    public PizzasPresenter(PizzasView view) {
        this.model = new PizzasModel();
        this.view = view;
    }

    public void loadProducts() {
        model.loadProducts(new PizzasModel.OnLoadProductsListener() {
            @Override
            public void onSuccess(ArrayList<Product> productList) {
                view.showProductsOnList(productList);
            }

            @Override
            public void onError(String errorMessageq) {
                view.showMessageError(errorMessageq);
            }
        });
    }

    public void showDialog(Product product, Map<String, Long> sizes){
        view.showDialog(product, sizes);
    }
}
