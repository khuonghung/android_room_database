package com.fu.product.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.fu.product.entities.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM product")
    LiveData<List<Product>> findAll();

    @Query("SELECT * FROM product WHERE id = :id")
    LiveData<Product> findById(int id);

    @Query("INSERT INTO product (name, description, price) VALUES (:name, :description, :price)")
    void save(String name, String description, double price);

    @Query("UPDATE product SET name = :name, description = :description, price = :price WHERE id = :id")
    void update(int id, String name, String description, double price);

    @Query("DELETE FROM product WHERE id = :id")
    void delete(int id);

    @Query("DELETE FROM product")
    void deleteAll();
}
