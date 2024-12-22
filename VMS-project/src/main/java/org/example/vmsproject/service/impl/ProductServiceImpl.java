package org.example.vmsproject.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.vmsproject.dto.ProductDTO;
import org.example.vmsproject.entity.Product;
import org.example.vmsproject.entity.Warehouse;
import org.example.vmsproject.exception.AppException;
import org.example.vmsproject.exception.ErrorCode;
import org.example.vmsproject.repository.ProductRepository;
import org.example.vmsproject.repository.WarehouseRepository;
import org.example.vmsproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.rmi.server.LogStream.log;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Override
    public List<Product> getAllProducts(Long warehouseId) {
        return productRepository.findAllByWarehouseId(warehouseId);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void addProduct(ProductDTO request) {
        Warehouse warehouse = warehouseRepository.findById(request.getWarehouse().getWarehouseId())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_WAREHOUSE));

        int newStock = warehouse.getCurrentStock() + request.getQuantity();
        if (newStock > warehouse.getCapacity()) {
            throw new AppException(ErrorCode.INVALID_CAPACITY);
        }

        Optional<Product> existingProduct = productRepository.findByProductNameAndWarehouse(
                request.getProductName(), request.getWarehouse()
        );

        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setQuantity(product.getQuantity() + request.getQuantity());
            warehouse.addCurrentStock(request.getQuantity());
            productRepository.save(product);
            warehouseRepository.save(warehouse);
        } else {
            Product product = Product.builder()
                    .productName(request.getProductName())
                    .price(request.getPrice())
                    .quantity(request.getQuantity())
                    .warehouse(request.getWarehouse())
                    .build();

            productRepository.save(product);
            warehouse.addCurrentStock(request.getQuantity());
            warehouseRepository.save(warehouse);
        }
    }




    @Override
    public Product updateProduct(Long id, ProductDTO request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_PRODUCT));
        Warehouse warehouse = product.getWarehouse();
        int oldQuantity = product.getQuantity();
        int newQuantity = request.getQuantity();
        int newStockAfterSubtraction = warehouse.getCurrentStock() - oldQuantity;
        if (newStockAfterSubtraction < 0) {
            throw new AppException(ErrorCode.INVALID_CAPACITY_ZERO);
        }
        int newStockAfterAddition = newStockAfterSubtraction + newQuantity;

        if (newStockAfterAddition > warehouse.getCapacity()) {
            throw new AppException(ErrorCode.INVALID_CAPACITY);
        }
        product.setProductName(request.getProductName());
        product.setPrice(request.getPrice());
        product.setQuantity(newQuantity);
        product.setWarehouse(request.getWarehouse());
        productRepository.save(product);

        warehouse.setCurrentStock(newStockAfterAddition);
        warehouseRepository.save(warehouse);

        return product;
    }


    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                        .orElseThrow(()-> new AppException(ErrorCode.INVALID_PRODUCT));
        Warehouse warehouse = product.getWarehouse();
        int newStock = warehouse.getCurrentStock() - product.getQuantity();
        if(newStock <0){
            newStock = 0;
        }

        warehouse.setCurrentStock(newStock);
        warehouseRepository.save(warehouse);
        productRepository.deleteById(id);

    }

    @Override
    public int totalProducts() {
        return productRepository.findTotalProducts();
    }
}
