package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.example.demo.model.User;

public interface UserRepository extends CrudRepository<User,Long> {
    

	
}
