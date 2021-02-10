package com.uirmfk.uirmfk.relation.repository;

import com.uirmfk.uirmfk.relation.model.ImageDescription;
import com.uirmfk.uirmfk.relation.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    Page<Item> findByItemId(String imageDescriptionId, Pageable pageable);
    Optional<Item> findByIdAndItemId(Long id, String imageDescriptionId);
}
