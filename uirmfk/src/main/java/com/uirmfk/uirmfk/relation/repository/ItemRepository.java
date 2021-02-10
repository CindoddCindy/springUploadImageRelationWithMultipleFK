package com.uirmfk.uirmfk.relation.repository;

import com.uirmfk.uirmfk.relation.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
}
