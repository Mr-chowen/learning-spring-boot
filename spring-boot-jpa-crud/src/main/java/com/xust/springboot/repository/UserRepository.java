package com.xust.springboot.repository;

import com.xust.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findById(long id);

    void deleteById(long id);

}
