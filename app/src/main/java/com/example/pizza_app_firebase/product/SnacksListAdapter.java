package com.example.pizza_app_firebase.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizza_app_firebase.R;
import com.example.pizza_app_firebase.presenter.SnacksPresenter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class SnacksListAdapter extends RecyclerView.Adapter<SnacksListAdapter.myViewHolder> {

    private Context             context;
    private ArrayList<Product>  productList;
    private SnacksPresenter     presenter;

    public SnacksListAdapter(Context context, ArrayList<Product> productList, SnacksPresenter presenter) {
        this.context        = context;
        this.productList    = productList;
        this.presenter      = presenter;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_snacks_layout, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Product product = productList.get(position);
        int idImage     = context.getResources().getIdentifier(product.image, "mipmap", context.getPackageName());

        holder.name     .setText(product.name.toUpperCase());
        holder.image    .setImageResource(idImage);

        NumberFormat myFormat
                        = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        myFormat        .setMaximumFractionDigits(0);
        holder.price    .setText(myFormat.format(product.price) + " COP");

        holder.mainLayout.setOnClickListener(v -> {
            presenter.showDialog(product,  position);
        });

        holder.name.setOnClickListener(v -> {
            presenter.showDialog(product,  position);
        });

        holder.price.setOnClickListener(v -> {
            presenter.showDialog(product,  position);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        private TextView    name;
        private TextView    price;
        private ImageView   image;
        private View        mainLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name        = itemView.findViewById(R.id.name);
            price       = itemView.findViewById(R.id.description);
            image       = itemView.findViewById(R.id.image);
            mainLayout  = itemView.findViewById(R.id.mainLayout);
        }
    }
}
