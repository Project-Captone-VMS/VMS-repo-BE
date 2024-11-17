package org.example.vmsproject.controller;

import org.example.vmsproject.dto.ProductDTO;
import org.example.vmsproject.entity.Product;
import org.example.vmsproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable("id") long id) {
        List<Product> products = productService.getAllProducts(id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO product) {
        String result = productService.addProduct(product);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id")long id,@RequestBody ProductDTO product) {
        String result = productService.updateProduct(id, product);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id")long id) {
        String result = productService.deleteProduct(id);
        return ResponseEntity.ok(result);
    }

}
