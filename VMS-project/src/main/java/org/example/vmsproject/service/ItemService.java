package org.example.vmsproject.service;

import org.example.vmsproject.dto.request.ItemRequest;
import org.example.vmsproject.entity.Item;


import java.util.List;

public interface ItemService {
     List<Item> getAllByWarehouseId(Long id);

    Item saveItem(ItemRequest request);

    Item updateItem(Long id, ItemRequest request);

    void deleteItem(Long id);
}
