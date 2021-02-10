package com.uirmfk.uirmfk.relation.repository;

import com.uirmfk.uirmfk.relation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
