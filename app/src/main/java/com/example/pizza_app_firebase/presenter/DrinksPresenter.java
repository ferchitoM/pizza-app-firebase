package com.example.pizza_app_firebase.presenter;

import com.example.pizza_app_firebase.model.DrinksModel;
import com.example.pizza_app_firebase.product.Product;
import com.example.pizza_app_firebase.view.DrinksView;

import java.util.ArrayList;
import java.util.Map;

public class DrinksPresenter {

    private DrinksModel model;
    private DrinksView view;

    public DrinksPresenter(DrinksView view) {
        this.model = new DrinksModel();
        this.view = view;
    }

    public void loadProducts() {
        model.loadProducts(new DrinksModel.OnLoadProductsListener() {
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

    public void showDialog(Product product, int index) {
        view.showDialog(product, index);
    }
}
