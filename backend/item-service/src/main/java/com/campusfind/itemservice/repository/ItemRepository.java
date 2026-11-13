package com.campusfind.itemservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.campusfind.itemservice.models.Item;
import com.campusfind.itemservice.models.ItemType;
import com.campusfind.itemservice.models.ItemStatus;

public interface ItemRepository extends JpaRepository<Item, Long> {
	public List<Item> findByType(ItemType type);
	public List<Item> findByCategory(String category);
	public List<Item> findByStatus(ItemStatus status);
	public List<Item> findByTitleContainingIgnoreCase(String title);
	public List<Item> findByTypeAndStatus(ItemType type, ItemStatus status);

}
