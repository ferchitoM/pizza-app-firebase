package com.example.pizza_app_firebase.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizza_app_firebase.MainActivity;
import com.example.pizza_app_firebase.R;
import com.example.pizza_app_firebase.presenter.CartPresenter;
import com.example.pizza_app_firebase.cart.CartListAdapter;
import com.example.pizza_app_firebase.cart.CartProduct;
import com.example.pizza_app_firebase.view.CartView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CartFragment extends Fragment implements CartView {

    private CartPresenter       presenter;

    View                        view;
    MainActivity                mainActivity;
    ArrayList<CartProduct>      productsArray;
    CartListAdapter             adapter;
    RecyclerView                lista;
    AlertDialog.Builder         builder;
    AlertDialog                 alertDialog;
    TextView                    message;
    Button                      payButton;

    public CartFragment() {
    }

    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        presenter       = new CartPresenter((CartFragment) this);
        message         = view.findViewById(R.id.message);
        payButton       = view.findViewById(R.id.payButton);

        LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getContext());
        lista           = view.findViewById(R.id.lista);
        lista           .setLayoutManager(layoutManager);
        lista           .setHasFixedSize(true);

        productsArray   = mainActivity.shoppingCart.products;
        adapter         = new CartListAdapter(getContext(), productsArray, presenter);
        lista           .setAdapter(adapter);

        if(productsArray.size() > 0) buttonPayment(true);
        else buttonPayment(false);

        payButton.setOnClickListener(v -> {

        });

        return view;
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
    public void showRemoveProductDialog(CartProduct product, int index) {

        String title;
        String msg;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        NumberFormat myFormat   = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        myFormat                .setMaximumFractionDigits(0);

        if(product.size != null) {
            // Pizza products
            title   = product.name.toUpperCase();
            msg     = product.amount + " " + product.size + " (" + myFormat.format(product.total) + " COP)";
            builder .setTitle(title);
            builder .setMessage(msg);
        }
        else {
            //Other products
            title   = product.amount + " " + product.name.toUpperCase() + " (" + myFormat.format(product.total) + " COP)";
            builder .setTitle(title);
        }

        builder.setPositiveButton("Quitar del carrito", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                Toast.makeText(getContext(), "El producto se ha borrado del carrito", Toast.LENGTH_SHORT).show();
                mainActivity.shoppingCart.removeProduct(index);
                adapter.notifyDataSetChanged();

                if(mainActivity.shoppingCart.getSize() == 0) {
                    buttonPayment(false);
                }

            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog  = builder.create();
        dialog.show();
    }

    @Override
    public void buttonPayment(boolean paymentVisibility) {
        if(paymentVisibility){
            payButton.setVisibility(View.VISIBLE);
            message.setVisibility(View.GONE);
        }
        else {
            payButton.setVisibility(View.GONE);
            message.setVisibility(View.VISIBLE);
        }
    }
}