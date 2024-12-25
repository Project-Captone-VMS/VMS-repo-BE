package org.example.vmsproject.service.impl;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.example.vmsproject.dto.request.ItemRequest;
import org.example.vmsproject.entity.Item;
import org.example.vmsproject.entity.Product;
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

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Item> getAllByWarehouseId(Long id){

        return itemRepository.findAllByWarehouseWarehouseId(id);
    }

    @Override
    public Item saveItem(ItemRequest request) {
//        Product product = new Product();
//        int newQuantity = product.getQuantity()-request.getQuantity();

                Item item = Item.builder()
                .itemName(request.getItemName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .warehouse(request.getWarehouse())
                .build();
        itemRepository.save(item);

//        product=productRepository.findByProductNameAndWarehouse(request.getWarehouse().getProducts().getP)
//        product = Product.builder()
//                .quantity(newQuantity)
//                .build();
//        productRepository.save(product);

        return item;
    }

    @Override
    public Item updateItem(Long id, ItemRequest request){

        Item item = itemRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ITEM_NOT_FOUND));
        item.setItemName(request.getItemName());
        item.setPrice(request.getPrice());
        item.setQuantity(request.getQuantity());
        itemRepository.save(item);

        Product product = new Product();
        int newQuantity = product.getQuantity()-request.getQuantity();
        product = Product.builder()
                .quantity(newQuantity)
                .build();
        productRepository.save(product);

        return item;
    }

    @Override
    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }


}
