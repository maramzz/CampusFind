
package com.campusfind.itemservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campusfind.itemservice.models.Item;
import com.campusfind.itemservice.models.ItemStatus;
import com.campusfind.itemservice.models.ItemType;
import com.campusfind.itemservice.service.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // create a new item
    @PostMapping
    public ResponseEntity<Item> createItem(
            @Valid @RequestBody Item item) {

        Item createdItem = itemService.createItem(item);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdItem);
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
    
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Item>> getItemsByType(
            @PathVariable ItemType type) {

        return ResponseEntity.ok(
                itemService.getItemsByType(type));
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Item>> getItemsByCategory(
            @PathVariable String category) {

        return ResponseEntity.ok(
                itemService.getItemsByCategory(category));
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Item>> getItemsByStatus(
            @PathVariable ItemStatus status) {

        return ResponseEntity.ok(itemService.getItemsByStatus(status));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Item>> searchItemsByTitle(
            @RequestParam String title) {

        return ResponseEntity.ok(itemService.searchItemsByTitle(title));
    }
    
    @GetMapping("/filter")
    public ResponseEntity<List<Item>> getItemsByTypeAndStatus(
            @RequestParam ItemType type,
            @RequestParam ItemStatus status) {

        return ResponseEntity.ok(
            itemService.getItemsByTypeAndStatus(type, status)
        );
    }

    // update an item
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(
            @PathVariable Long id,
            @Valid @RequestBody Item item) {

        return ResponseEntity.ok(
                itemService.updateItem(id, item));
    }
    

    // delete an item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
