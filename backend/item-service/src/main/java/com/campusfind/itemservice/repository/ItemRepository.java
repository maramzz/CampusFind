package com.campusfind.itemservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campusfind.itemservice.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
