package com.example.pizza_app_firebase.presenter;

import com.example.pizza_app_firebase.model.MenuModel;
import com.example.pizza_app_firebase.product.Product;
import com.example.pizza_app_firebase.view.MenuView;

import java.util.ArrayList;
import java.util.Map;

public class MenuPresenter {

    private MenuModel model;
    private MenuView view;

    public MenuPresenter(MenuView view) {
        this.model = new MenuModel();
        this.view = view;
    }

    public void loadProducts() {
        model.loadProducts(new MenuModel.OnLoadProductsListener() {
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
