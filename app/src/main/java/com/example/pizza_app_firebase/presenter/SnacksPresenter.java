package com.example.pizza_app_firebase.presenter;

import com.example.pizza_app_firebase.model.SnacksModel;
import com.example.pizza_app_firebase.product.Product;
import com.example.pizza_app_firebase.view.SnacksView;

import java.util.ArrayList;
import java.util.Map;

public class SnacksPresenter {

    private SnacksModel model;
    private SnacksView view;

    public SnacksPresenter(SnacksView view) {
        this.model = new SnacksModel();
        this.view = view;
    }

    public void loadProducts() {
        model.loadProducts(new SnacksModel.OnLoadProductsListener() {
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

    public void showDialog(Product product, int index){
        view.showDialog(product, index);
    }
}
