package com.fu.product.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fu.product.R;
import com.fu.product.entities.Product;
import com.fu.product.repository.ProductRepository;
import com.fu.product.ui.adapter.ProductAdapter;
import com.fu.product.ui.fragment.ProductInsertDialog;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private LiveData<List<Product>> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingView();
        getProduct();
        initRecyclerView();
    }

    public void getProduct() {
        ProductRepository productRepository = new ProductRepository();
        productList = productRepository.findAll();
    }

    private void bindingView() {
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList.observe(this, products -> {
            ProductAdapter productAdapter = new ProductAdapter(products, this);
            recyclerView.setAdapter(productAdapter);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_product) {
            openDialog();
        } else if (item.getItemId() == R.id.action_refresh) {
            getProduct();
            initRecyclerView();
            Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openDialog() {
        ProductInsertDialog productInsertDialog = new ProductInsertDialog();
        productInsertDialog.show(getSupportFragmentManager(), "Product Insert Dialog");
    }

}