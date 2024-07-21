package com.example.pizza_app_firebase.model;

import androidx.annotation.NonNull;

import com.example.pizza_app_firebase.product.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuModel {

    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private ValueEventListener eventListener;
    private ArrayList<Product> products = new ArrayList<Product>();

    public MenuModel() {
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public void showProducts(){

    }

    public void loadProducts(OnLoadProductsListener interfaceListener) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userUID = currentUser.getUid();

            DatabaseReference ref = database.getReference()
                    .child("users")
                    .child(userUID)
                    .child("products")
                    .child("Pizzas");

            eventListener = ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot productSnapshot : snapshot.getChildren()){
                        Product item = productSnapshot.getValue(Product.class);
                        if(item != null) products.add(item);
                    }
                    interfaceListener.onSuccess(products);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    interfaceListener.onError("Se canceló la descarga de los datos.");
                }
            });

        } else interfaceListener.onError("No se ha iniciado sesión.");

    }

    public interface OnLoadProductsListener {
        void onSuccess(ArrayList<Product> products);
        void onError(String errorMessage);
    }
}
