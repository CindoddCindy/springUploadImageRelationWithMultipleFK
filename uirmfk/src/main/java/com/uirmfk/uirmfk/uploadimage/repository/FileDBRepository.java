package com.uirmfk.uirmfk.uploadimage.repository;


import com.uirmfk.uirmfk.uploadimage.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
}
