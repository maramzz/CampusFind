
package com.campusfind.itemservice.controller;

import com.campusfind.itemservice.models.Item;
import com.campusfind.itemservice.service.ItemService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // create a new item
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item createdItem = itemService.createItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    // get all items
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    // get one item by ID
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    // update an item
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(
            @PathVariable Long id,
            @RequestBody Item item) {
        return ResponseEntity.ok(itemService.updateItem(id, item));
    }

    // delete an item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
