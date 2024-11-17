package org.example.vmsproject.service;

import org.example.vmsproject.dto.ProductDTO;
import org.example.vmsproject.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts(Long warehouseId);
    Product getProductById(Long id);
    String addProduct(ProductDTO product);
    String updateProduct(Long id,ProductDTO product);
    String deleteProduct(Long id);
}
