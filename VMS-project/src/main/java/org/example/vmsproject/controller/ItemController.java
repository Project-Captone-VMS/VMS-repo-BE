package org.example.vmsproject.controller;

import org.example.vmsproject.dto.request.CreateShipment;
import org.example.vmsproject.dto.request.ItemRequest;
import org.example.vmsproject.entity.Item;
import org.example.vmsproject.entity.Shipment;
import org.example.vmsproject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item/")
@CrossOrigin(origins = "*")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("getAll/{id}")
    public ResponseEntity<List<Item>>getAll(@PathVariable("id") Long id){
        List<Item> itemList = itemService.getAllByShipmentId(id);
        return ResponseEntity.ok(itemList);
    }
    @PostMapping("save")
    public ResponseEntity<?>save(@RequestBody ItemRequest request){
        Item item = itemService.saveItem(request);
        return ResponseEntity.ok(item);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<?>update(@PathVariable("id") Long id, @RequestBody ItemRequest request){
        Item item = itemService.updateItem(id, request);
        return ResponseEntity.ok(item);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id") Long id){
        itemService.deleteItem(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
