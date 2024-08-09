package com.example.pizza_app_firebase.model;

import com.example.pizza_app_firebase.cart.CartProduct;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartModel {

    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private ValueEventListener eventListener;
    private ArrayList<CartProduct> products = new ArrayList<CartProduct>();

    public CartModel() {
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public void saveShoppingCart(OnSaveShoppingCartListener interfaceListener) {
        /*FirebaseUser currentUser = mAuth.getCurrentUser();
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
                        CartProduct item = productSnapshot.getValue(CartProduct.class);
                        if(item != null) products.add(item);
                    }
                    interfaceListener.onSuccess(products);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    interfaceListener.onError("Se canceló la descarga de los datos.");
                }
            });

        } else interfaceListener.onError("No se ha iniciado sesión.");*/

    }

    public interface OnSaveShoppingCartListener {
        void onSuccess(ArrayList<CartProduct> products);
        void onError(String errorMessage);
    }
}
