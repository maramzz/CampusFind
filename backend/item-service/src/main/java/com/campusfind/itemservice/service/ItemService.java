
package com.campusfind.itemservice.service;

import com.campusfind.itemservice.models.Item;
import com.campusfind.itemservice.repository.ItemRepository;

import org.springframework.stereotype.Service;

import java.util.List;

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
        return itemRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Item not found with id: " + id));
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
