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
import com.example.pizza_app_firebase.presenter.MenuPresenter;
import com.example.pizza_app_firebase.product.Product;
import com.example.pizza_app_firebase.product.ProductListAdapter;
import com.example.pizza_app_firebase.product.Size;
import com.example.pizza_app_firebase.shopping.CartProduct;
import com.example.pizza_app_firebase.view.MenuView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class MenuFragment extends Fragment implements MenuView {

    private MenuPresenter   presenter;


    View                    view;
    MainActivity            mainActivity;
    ArrayList<Product>      productsArray;
    ProductListAdapter      adapter;
    RecyclerView            lista;
    AlertDialog.Builder     builder;
    AlertDialog             alertDialog;

    public MenuFragment() {

    }

    public static MenuFragment newInstance() {
        MenuFragment fragment   = new MenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view            = inflater.inflate(R.layout.fragment_menu, container, false);

        mainActivity    = (MainActivity) getActivity();
        presenter       = new MenuPresenter((MenuFragment) this);

        LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getContext());
        lista           = view.findViewById(R.id.lista);
        lista           .setLayoutManager(layoutManager);
        lista           .setHasFixedSize(true);

        productsArray   = new ArrayList<Product>();
        adapter         = new ProductListAdapter(getContext(), productsArray, presenter);
        lista           .setAdapter(adapter);

        presenter       .loadProducts();

        return          view;
    }

    @Override
    public void showProductsOnList(ArrayList<Product> productList) {
        productsArray   .clear();
        productsArray   .addAll(productList);

        adapter         .notifyDataSetChanged();
    }

    @Override
    public void showMessageError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadProducts() {

    }

    @Override
    public void showDialog(Product product, Map<String, Long> sizes){

        SizesArray sizesArray = mapToArray(sizes);
        final int[] selectedOption = new int[1];

        AlertDialog.Builder builder
                            = new AlertDialog.Builder(getActivity());
        builder             .setTitle("DESEAS " + product.name.toUpperCase());
        builder             .setSingleChoiceItems(sizesArray.options, 0, (dialog, which) -> {
                            selectedOption[0] = which;
        });

        builder.setPositiveButton("Agregar al carrito", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                // ((MainActivity) getActivity()).switchFragment(new ShoppingCarFragment());
                // ((MainActivity) getActivity()).selectShoppingCartIcon();

                CartProduct newProduct  = new CartProduct();
                newProduct.id           = mainActivity.shoppingCart.newId();
                newProduct.name         = product.name;
                newProduct.image        = product.image;
                newProduct.size         = sizesArray.names[selectedOption[0]];
                newProduct.price        = sizesArray.prices[selectedOption[0]];
                newProduct.amount       = 1L; //Luego se puede cambiar en el carrito de compra
                newProduct              .calculateValues();

                mainActivity            .shoppingCart.addProduct(newProduct);

                Toast.makeText(getContext(), "Producto agregado al carrito", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancels the dialog.
            }
        });

        AlertDialog dialog  = builder.create();
        dialog              .show();
    }

    private SizesArray mapToArray(Map<String, Long> sizes){

        ArrayList<Size> sizesList = new ArrayList<Size>();
        sizes.forEach((key, value) -> sizesList.add(new Size(key, value)));

        int i = 0;
        String[] sizesNames     = new String[sizesList.size()];
        Long[] sizesPrices      = new Long[sizesList.size()];
        String[] sizesOptions   = new String[sizesList.size()];

        NumberFormat myFormat   = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        myFormat                .setMaximumFractionDigits(0);

        for(Size item : sizesList){
            sizesNames[i]       = item.name;
            sizesPrices[i]      = item.price;
            sizesOptions[i]     = item.name.toUpperCase() + ": " + myFormat.format(item.price) + " COP";            i++;
        }
        return new SizesArray(sizesNames, sizesPrices, sizesOptions);
    }

    private class SizesArray {
        public String[] names;
        public Long[] prices;
        public String[] options;

        public SizesArray(String[] names, Long[] prices, String[] options) {
            this.names = names;
            this.prices = prices;
            this.options = options;
        }
    }
}