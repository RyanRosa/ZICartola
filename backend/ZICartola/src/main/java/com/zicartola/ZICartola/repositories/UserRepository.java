package com.zicartola.ZICartola.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zicartola.ZICartola.entites.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
