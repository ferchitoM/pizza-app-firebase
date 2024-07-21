package com.example.pizza_app_firebase.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizza_app_firebase.MainActivity;
import com.example.pizza_app_firebase.R;
import com.example.pizza_app_firebase.presenter.CartPresenter;
import com.example.pizza_app_firebase.shopping.CartListAdapter;
import com.example.pizza_app_firebase.shopping.CartProduct;
import com.example.pizza_app_firebase.view.CartView;

import java.util.ArrayList;

public class ShoppingCarFragment extends Fragment implements CartView {

    private CartPresenter       presenter;


    View                        view;
    MainActivity                mainActivity;
    ArrayList<CartProduct>      productsArray;
    CartListAdapter             adapter;
    RecyclerView                lista;
    AlertDialog.Builder         builder;
    AlertDialog                 alertDialog;

    public ShoppingCarFragment() {
    }

    public static ShoppingCarFragment newInstance(String param1, String param2) {
        ShoppingCarFragment fragment = new ShoppingCarFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view            = inflater.inflate(R.layout.fragment_shopping_car, container, false);

        mainActivity    = (MainActivity) getActivity();
        presenter       = new CartPresenter((ShoppingCarFragment) this);

        LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getContext());
        lista           = view.findViewById(R.id.lista);
        lista           .setLayoutManager(layoutManager);
        lista           .setHasFixedSize(true);

        productsArray   = mainActivity.shoppingCart.products;
        adapter         = new CartListAdapter(getContext(), productsArray, presenter);
        lista           .setAdapter(adapter);

        productsArray.forEach(product -> {
           Log.e("Producto", product.name);
        });

        return          view;
    }


    @Override
    public void saveShoppingCart(ArrayList<CartProduct> productList) {
        /*productsArray   .clear();
        productsArray   .addAll(productList);

        adapter         .notifyDataSetChanged();*/
    }

    @Override
    public void showMessageError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRemoveProductDialog(CartProduct product) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(product.name.toUpperCase());

        builder.setPositiveButton("Quitar del carrito", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                mainActivity.shoppingCart.removeProduct(product.id);
                Toast.makeText(getContext(), "El producto se ha borrado del carrito", Toast.LENGTH_SHORT).show();

            }
        });

        AlertDialog dialog  = builder.create();
        dialog.show();
    }
}