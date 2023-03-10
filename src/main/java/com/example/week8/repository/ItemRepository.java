package com.example.week8.repository;

import com.example.week8.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    List<Item> findByItemNoContainingAndItemNameContaining(String itemNo,String itemName);

}
