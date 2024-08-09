package com.example.pizza_app_firebase.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
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
import com.example.pizza_app_firebase.cart.CartProduct;
import com.example.pizza_app_firebase.presenter.SnacksPresenter;
import com.example.pizza_app_firebase.presenter.SnacksPresenter;
import com.example.pizza_app_firebase.product.SnacksListAdapter;
import com.example.pizza_app_firebase.product.Product;
import com.example.pizza_app_firebase.product.SnacksListAdapter;
import com.example.pizza_app_firebase.view.PizzasView;
import com.example.pizza_app_firebase.view.SnacksView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class SnacksFragment extends Fragment implements SnacksView {

    private ArrayList<Product>  products = new ArrayList<Product>();
    private SnacksPresenter     presenter;

    View                        view;
    MainActivity                mainActivity;
    SnacksListAdapter           adapter;
    RecyclerView                lista;
    AlertDialog.Builder         builder;
    AlertDialog                 alertDialog;

    public SnacksFragment() {
    }

    public static SnacksFragment newInstance(String param1, String param2) {
        SnacksFragment fragment = new SnacksFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view            = inflater.inflate(R.layout.fragment_snacks, container, false);

        mainActivity    = (MainActivity) getActivity();
        presenter       = new SnacksPresenter((SnacksFragment) this);

        LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getContext());
        lista           = view.findViewById(R.id.lista);
        lista           .setLayoutManager(layoutManager);
        lista           .setHasFixedSize(true);

        adapter         = new SnacksListAdapter(getContext(), products, presenter);
        lista           .setAdapter(adapter);

        presenter       .loadProducts();

        return          view;
    }

    @Override
    public void showProductsOnList(ArrayList<Product> productList) {
        products    .clear();
        products    .addAll(productList);

        adapter     .notifyDataSetChanged();
    }

    @Override
    public void showMessageError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadProducts() {

    }

    @Override
    public void showDialog(Product product, int index) {

        String title            = product.name.toUpperCase();

        NumberFormat myFormat   = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        myFormat                .setMaximumFractionDigits(0);
        String msg              = myFormat.format(product.price) + " COP";

        AlertDialog.Builder builder
                                = new AlertDialog.Builder(getActivity());
        builder                 .setTitle(title + " (" + msg + ")");

        builder.setPositiveButton("Agregar al carrito", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                CartProduct newProduct  = new CartProduct();
                newProduct.id           = mainActivity.shoppingCart.newId();
                newProduct.name         = product.name;
                newProduct.image        = product.image;
                newProduct.size         = null;
                newProduct.price        = product.price;
                newProduct.amount       = 1L; //Luego se puede cambiar en el carrito de compra
                newProduct              .calculateValues();

                mainActivity            .shoppingCart.addProduct(newProduct);

                Toast.makeText(getContext(), "Producto agregado al carrito", Toast.LENGTH_SHORT).show();

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
}