package com.fu.product.repository;

import androidx.lifecycle.LiveData;

import com.fu.product.dao.ProductDao;
import com.fu.product.database.ProductDatabase;
import com.fu.product.entities.Product;

import java.util.List;

public class ProductRepository {
    private final ProductDao productDao;
    private final LiveData<List<Product>> allProduct;

    public ProductRepository() {
        ProductDatabase db = ProductDatabase.getDatabase();
        productDao = db.productDao();
        allProduct = productDao.findAll();
    }

    public LiveData<List<Product>> findAll() {
        return allProduct;
    }

    public LiveData<Product> findById(int id) {
        return productDao.findById(id);
    }

    public void save(Product product) {
        ProductDatabase.databaseWriteExecutor.execute(() -> productDao.save(product.getName(), product.getDescription(), product.getPrice()));
    }

    public void update(Product product) {
        ProductDatabase.databaseWriteExecutor.execute(() -> productDao.update(product.getId(), product.getName(), product.getDescription(), product.getPrice()));
    }

    public void delete(int id) {
        ProductDatabase.databaseWriteExecutor.execute(() -> productDao.delete(id));
    }
}
