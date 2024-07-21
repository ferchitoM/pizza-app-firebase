package com.example.pizza_app_firebase.shopping;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizza_app_firebase.MainActivity;
import com.example.pizza_app_firebase.R;
import com.example.pizza_app_firebase.presenter.CartPresenter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.myViewHolder> {

    private Context context;
    private ArrayList<CartProduct> productList;
    private CartPresenter presenter;
    private MainActivity mainActivity;

    public CartListAdapter(Context context, ArrayList<CartProduct> productList, CartPresenter presenter) {
        this.context        = context;
        this.productList    = productList;
        this.presenter      = presenter;

        this.mainActivity    = (MainActivity) getActivity(context);

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_cart_product_layout, parent, false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        CartProduct product     = productList.get(position);

        int idImage             = context.getResources().getIdentifier(product.image, "mipmap", context.getPackageName());

        holder.image            .setImageResource(idImage);
        holder.name             .setText(product.name.toUpperCase());
        holder.size             .setText(product.amount + " " +product.size);

        NumberFormat myFormat   = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        myFormat                .setMaximumFractionDigits(0);
        holder.total            .setText(myFormat.format(product.total) + " COP");

        holder.buttonAdd.setOnClickListener(v -> {

            product.amount++;
            product         .calculateValues();
            holder.size     .setText(product.amount + " " +product.size);
            holder.total    .setText(myFormat.format(product.total) + " COP");

            productList.set(position, product);
            mainActivity.shoppingCart.products.set(position, product);


        });

        holder.buttonRemove.setOnClickListener(v -> {
            if (product.amount > 1) {
                product.amount--;
                product         .calculateValues();
                holder.size     .setText(product.amount + " " +product.size);
                holder.total    .setText(myFormat.format(product.total) + " COP");

                productList.set(position, product);
                mainActivity.shoppingCart.products.set(position, product);

            }
            else {
                productList.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        private TextView    name;
        private ImageView   image;
        private TextView    size;
        private TextView    total;
        private ImageButton buttonAdd;
        private ImageButton buttonRemove;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name        = itemView.findViewById(R.id.name);
            image       = itemView.findViewById(R.id.image);
            size        = itemView.findViewById(R.id.size);
            total       = itemView.findViewById(R.id.total);
            buttonAdd   = itemView.findViewById(R.id.buttonAdd);
            buttonRemove= itemView.findViewById(R.id.buttonRemove);


        }
    }

}
