package org.example.vmsproject.service.impl;

import org.example.vmsproject.dto.ProductDTO;
import org.example.vmsproject.entity.Product;
import org.example.vmsproject.repository.ProductRepository;
import org.example.vmsproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductSeriveImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts(Long warehouseId) {
        return productRepository.findAllByWarehouseId(warehouseId);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public String addProduct(ProductDTO product) {
        Product newProduct = new Product();
        newProduct.setProductName(product.getProductName());
        newProduct.setPrice(product.getPrice());
        newProduct.setQuantity(product.getQuantity());
        newProduct.setWarehouse(product.getWarehouse());
        productRepository.save(newProduct);
        return "Product added successfully";
    }

    @Override
    public String updateProduct(Long id, ProductDTO productDTO) {
        return productRepository.findById(id).map(product -> {
            product.setProductName(productDTO.getProductName());
            product.setPrice(productDTO.getPrice());
            product.setQuantity(productDTO.getQuantity());
            product.setWarehouse(productDTO.getWarehouse());
            productRepository.save(product);
            return "Product updated successfully";
        }).orElse("Product not found");
    }

    @Override
    public String deleteProduct(Long id) {
        productRepository.deleteById(id);
        return "Product deleted successfully";
    }


}
