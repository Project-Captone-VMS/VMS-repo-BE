package org.example.vmsproject.service.impl;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.example.vmsproject.dto.request.ItemRequest;
import org.example.vmsproject.entity.Item;
import org.example.vmsproject.entity.Product;
import org.example.vmsproject.entity.Shipment;
import org.example.vmsproject.entity.Warehouse;
import org.example.vmsproject.exception.AppException;
import org.example.vmsproject.exception.ErrorCode;
import org.example.vmsproject.repository.ItemRepository;
import org.example.vmsproject.repository.ProductRepository;
import org.example.vmsproject.repository.WarehouseRepository;
import org.example.vmsproject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Item> getAllByShipmentId(Long id){

        return itemRepository.findAllByShipmentId(id);
    }

    @Override
    public Item saveItem(ItemRequest request) {
        Optional<Item> existingItem = itemRepository.findByShipmentIdAndItemName(
                request.getShipment().getShipmentId(), request.getItemName());

        if (existingItem.isPresent()) {
            Item item = existingItem.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());
            itemRepository.save(item);
            return item;
        }

        Optional<Product> existingProduct = productRepository.findByProductNameAndWarehouse(
                request.getItemName(),
                request.getWarehouse()
        );

        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            int newQuantity = product.getQuantity() - request.getQuantity();

            if (newQuantity < 0) {
                throw new AppException(ErrorCode.INVALID_CAPACITY);
            }

            product.setQuantity(newQuantity);
            productRepository.save(product);
        } else {
            throw new AppException(ErrorCode.INVALID_PRODUCT);
        }

        Item newItem = Item.builder()
                .itemName(request.getItemName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .warehouse(request.getWarehouse())
                .shipment(request.getShipment())
                .build();
        itemRepository.save(newItem);

        return newItem;
    }




    @Override
    public Item updateItem(Long id, ItemRequest request) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ITEM_NOT_FOUND));
        Optional<Product> existingProduct = productRepository.findByProductNameAndWarehouse(
                request.getItemName(),
                request.getWarehouse()
        );

        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            int quantityDifference = request.getQuantity() - item.getQuantity();
            int newQuantity = product.getQuantity() - quantityDifference;

            if (newQuantity < 0) {
                throw new AppException(ErrorCode.INVALID_CAPACITY);
            }
            product.setQuantity(newQuantity);
            productRepository.save(product);
        } else {
            throw new AppException(ErrorCode.INVALID_PRODUCT);
        }
        item.setItemName(request.getItemName());
        item.setPrice(request.getPrice());
        item.setQuantity(request.getQuantity());
        item.setWarehouse(request.getWarehouse());
        return itemRepository.save(item);
    }


    @Override
    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }


}
