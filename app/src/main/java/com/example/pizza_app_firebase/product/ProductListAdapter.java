package com.example.pizza_app_firebase.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizza_app_firebase.R;
import com.example.pizza_app_firebase.presenter.MenuPresenter;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.myViewHolder> {

    private Context             context;
    private ArrayList<Product>  productList;
    private MenuPresenter       presenter;

    public ProductListAdapter(Context context, ArrayList<Product> productList, MenuPresenter presenter) {
        this.context        = context;
        this.productList    = productList;
        this.presenter      = presenter;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_product_layout, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Product product = productList.get(position);
        int idImage     = context.getResources().getIdentifier(product.image, "mipmap", context.getPackageName());

        holder.name     .setText(product.name.toUpperCase());
        holder.image    .setImageResource(idImage);

        holder.mainLayout.setOnClickListener(v -> {
            presenter.showDialog(product,  product.sizes);
            Toast.makeText(context, product.name, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        private TextView    name;
        private ImageView   image;
        private View        mainLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name        = itemView.findViewById(R.id.name);
            image       = itemView.findViewById(R.id.image);
            mainLayout  = itemView.findViewById(R.id.mainLayout);
        }
    }
}
