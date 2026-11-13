
package com.campusfind.itemservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.campusfind.itemservice.exceptions.ItemNotFoundException;
import com.campusfind.itemservice.models.Item;
import com.campusfind.itemservice.models.ItemStatus;
import com.campusfind.itemservice.models.ItemType;
import com.campusfind.itemservice.repository.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }
    
    public List<Item> getItemsByType(ItemType type) {
        return itemRepository.findByType(type);
    }
    public List<Item> getItemsByCategory(String category) {
        return itemRepository.findByCategory(category);
    }
    public List<Item> getItemsByStatus(ItemStatus status) {
        return itemRepository.findByStatus(status);
    }
    public List<Item> searchItemsByTitle(String title) {
        return itemRepository.findByTitleContainingIgnoreCase(title);
    }
    
    public List<Item> getItemsByTypeAndStatus(ItemType type, ItemStatus status) {
        return itemRepository.findByTypeAndStatus(type, status);
    }

    public Item updateItem(Long id, Item updatedItem) {
        Item existingItem = getItemById(id);

        existingItem.setTitle(updatedItem.getTitle());
        existingItem.setDescription(updatedItem.getDescription());
        existingItem.setCategory(updatedItem.getCategory());
        existingItem.setType(updatedItem.getType());
        existingItem.setLocation(updatedItem.getLocation());
        existingItem.setDateOfIncident(updatedItem.getDateOfIncident());
        existingItem.setStatus(updatedItem.getStatus());
        existingItem.setImageUrl(updatedItem.getImageUrl());

        return itemRepository.save(existingItem);
    }

    public void deleteItem(Long id) {
        Item item = getItemById(id);
        itemRepository.delete(item);
    }
}
