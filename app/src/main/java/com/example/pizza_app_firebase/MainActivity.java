package com.example.pizza_app_firebase;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pizza_app_firebase.databinding.ActivityMainBinding;
import com.example.pizza_app_firebase.fragments.DrinksFragment;
import com.example.pizza_app_firebase.fragments.MenuFragment;
import com.example.pizza_app_firebase.fragments.ShoppingCarFragment;
import com.example.pizza_app_firebase.fragments.SnacksFragment;
import com.example.pizza_app_firebase.shopping.ShoppingCart;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    public ShoppingCart shoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        shoppingCart = new ShoppingCart();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.navBar.setItemIconTintList(null);

        binding.navBar.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if(id == R.id.buttonProducts) switchFragment(new MenuFragment());
            if(id == R.id.buttonSnacks) switchFragment(new SnacksFragment());
            if(id == R.id.buttonDrinks) switchFragment(new DrinksFragment());
            if(id == R.id.buttonCart) switchFragment(new ShoppingCarFragment());

            return true;
        });

        switchFragment(new MenuFragment());
    }

    public void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.myFrameLayout, fragment);
        fragmentTransaction.commit();

    }

    public void selectShoppingCartIcon(){

        int id = binding.navBar.getSelectedItemId();
        BottomNavigationItemView itemNavBar = binding.navBar.findViewById(id);
        itemNavBar.setChecked(false);

        itemNavBar = binding.navBar.findViewById(R.id.buttonCart);
        itemNavBar.setChecked(true);
    }

}