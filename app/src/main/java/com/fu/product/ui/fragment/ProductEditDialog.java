package com.fu.product.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.LiveData;

import com.fu.product.R;
import com.fu.product.entities.Product;
import com.fu.product.repository.ProductRepository;

public class ProductEditDialog extends AppCompatDialogFragment {
    private EditText edtProductName;
    private EditText edtProductPrice;
    private EditText edtProductDescription;
    private int id;

    public ProductEditDialog(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.product_edit, null);
        bindingView(view);
        getData();
        builder.setView(view)
                .setNegativeButton("Cancel", (dialog, which) -> {

                })
                .setPositiveButton("Save", (dialog, which) -> {
                    editProduct();
                    dialogSuccess();
                });
        return builder.create();
    }

    private void bindingView(View view) {
        edtProductName = view.findViewById(R.id.etName);
        edtProductPrice = view.findViewById(R.id.etPrice);
        edtProductDescription = view.findViewById(R.id.etDescription);
    }

    private void getData() {
        ProductRepository productRepository = new ProductRepository();
        LiveData<Product> productLiveData = productRepository.findById(id);
        productLiveData.observe(this, product -> {
            edtProductName.setText(product.getName());
            edtProductPrice.setText(String.valueOf(product.getPrice()));
            edtProductDescription.setText(product.getDescription());
        });
    }

    private void editProduct() {
        String name = edtProductName.getText().toString();
        double price = Double.parseDouble(edtProductPrice.getText().toString());
        String description = edtProductDescription.getText().toString();
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        ProductRepository productRepository = new ProductRepository();
        productRepository.update(product);
    }

    private void dialogSuccess() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Save product")
                .setMessage("Save product success")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }
}
