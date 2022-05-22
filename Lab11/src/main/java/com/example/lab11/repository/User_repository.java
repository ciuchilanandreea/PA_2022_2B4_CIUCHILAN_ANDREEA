package com.example.lab11.repository;

import com.example.lab11.entity.User_entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Repository
public interface User_repository extends JpaRepository<User_entity,Integer> {
    //
    List<User_entity> findAll();
    <User extends User_entity> User save(User entity);

    User_entity getUserByIdUser(long idUser);

    void deleteUserByIdUser(long idUser);

}
