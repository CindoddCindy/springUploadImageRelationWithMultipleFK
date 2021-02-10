package com.uirmfk.uirmfk.relation.repository;

import com.uirmfk.uirmfk.relation.model.ImageDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageDescriptionRepository extends JpaRepository<ImageDescription,String> {
    Page<ImageDescription> findByFileDbId(String fileDbId, Pageable pageable);
    Optional<ImageDescription> findByIdAndFileDbId(String id, String FileDbId);
}
