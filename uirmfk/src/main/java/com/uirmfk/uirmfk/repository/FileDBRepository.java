package com.uirmfk.uirmfk.repository;


import com.uirmfk.uirmfk.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
}
