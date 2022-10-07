package com.fu.product.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.product.R;
import com.fu.product.entities.Product;
import com.fu.product.ui.adapter.viewHolder.ProductViewHolder;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private static List<Product> products;
    private final Context context;
    private final LayoutInflater layoutInflater;

    public ProductAdapter(List<Product> productList, Context context) {
        this.products = productList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.setProductItem(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
