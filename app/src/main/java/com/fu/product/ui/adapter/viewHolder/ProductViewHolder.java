package com.fu.product.ui.adapter.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.product.R;
import com.fu.product.entities.Product;
import com.fu.product.repository.ProductRepository;
import com.fu.product.ui.activity.MainActivity;
import com.fu.product.ui.fragment.ProductEditDialog;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    private TextView tvProductId;
    private TextView tvProductName;
    private TextView tvProductDescription;
    private TextView tvProductPrice;
    private Button btnEdit;
    private Button btnDelete;
    private final Context context;

    public ProductViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        bindingView(itemView);
        bindingAction();
    }

    private void bindingView(View itemView) {
        tvProductId = itemView.findViewById(R.id.product_id);
        tvProductName = itemView.findViewById(R.id.product_name);
        tvProductDescription = itemView.findViewById(R.id.product_description);
        tvProductPrice = itemView.findViewById(R.id.product_price);
        btnEdit = itemView.findViewById(R.id.product_edit);
        btnDelete = itemView.findViewById(R.id.product_delete);
    }

    private void bindingAction() {
        btnDelete.setOnClickListener(v -> confirmDeleteProduct());
        btnEdit.setOnClickListener(v -> editProduct());
    }

    private void deleteProduct() {
        int productId = Integer.parseInt(tvProductId.getText().toString());
        ProductRepository productRepository = new ProductRepository();
        productRepository.delete(productId);
    }

    private void editProduct() {
        int productId = Integer.parseInt(tvProductId.getText().toString());
        ProductEditDialog productEditDialog = new ProductEditDialog(productId);
        productEditDialog.show(((MainActivity) context).getSupportFragmentManager(), "Edit Product");
    }


    public void setProductItem(Product product) {
        tvProductId.setText(String.valueOf(product.getId()));
        tvProductName.setText(product.getName());
        tvProductDescription.setText(product.getDescription());
        tvProductPrice.setText(String.valueOf(product.getPrice()));
    }

    private void confirmDeleteProduct() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Product")
                .setMessage("Are you sure you want to delete this product?")
                .setNegativeButton("Cancel", (dialog, which) -> {

                })
                .setPositiveButton("Delete", (dialog, which) -> deleteProduct());
        builder.create().show();
    }
}
