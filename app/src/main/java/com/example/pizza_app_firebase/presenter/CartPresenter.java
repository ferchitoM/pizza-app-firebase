package com.example.pizza_app_firebase.presenter;

import com.example.pizza_app_firebase.model.CartModel;
import com.example.pizza_app_firebase.cart.CartProduct;
import com.example.pizza_app_firebase.view.CartView;

import java.util.ArrayList;

public class CartPresenter {

    private CartModel model;
    private CartView view;

    public CartPresenter(CartView view) {
        this.model = new CartModel();
        this.view = view;
    }

    public void showShoppingCart() {
        model.saveShoppingCart(new CartModel.OnSaveShoppingCartListener() {
            @Override
            public void onSuccess(ArrayList<CartProduct> productList) {
                view.saveShoppingCart(productList);
            }

            @Override
            public void onError(String errorMessageq) {
                view.showMessageError(errorMessageq);
            }
        });
    }

    public void showRemoveProductDialog(CartProduct product, int index){
        view.showRemoveProductDialog(product, index);
    }

    public void buttonPayment(boolean paymentVisibility){
        view.buttonPayment(paymentVisibility);
    }
}
