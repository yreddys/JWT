package com.reddy.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reddy.jwt.entity.user;

@Repository
public interface UserRepo extends JpaRepository<user,Integer>{

	user findByUname(String username);

}
