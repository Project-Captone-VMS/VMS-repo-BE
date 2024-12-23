package org.example.vmsproject.controller;

<<<<<<< HEAD
=======
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
>>>>>>> e0365414c7856d470cc05c348c4f5bb44cabc985
import org.example.vmsproject.dto.ProductDTO;
import org.example.vmsproject.entity.Product;
import org.example.vmsproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.http.HttpStatus;
=======
>>>>>>> e0365414c7856d470cc05c348c4f5bb44cabc985
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
<<<<<<< HEAD
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
=======
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "*", maxAge = 3600)
>>>>>>> e0365414c7856d470cc05c348c4f5bb44cabc985
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
<<<<<<< HEAD
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO request) {
        productService.addProduct(request);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id")long id,@RequestBody ProductDTO product) {
        productService.updateProduct(id, product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id")long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/totalProduct")
    public ResponseEntity<?> getTotalLess() {
        int result = productService.totalProducts();
=======
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
>>>>>>> e0365414c7856d470cc05c348c4f5bb44cabc985
        return ResponseEntity.ok(result);
    }

}
