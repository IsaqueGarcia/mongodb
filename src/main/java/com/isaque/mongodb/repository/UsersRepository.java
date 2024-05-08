package com.isaque.mongodb.repository;

import com.isaque.mongodb.dto.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository extends MongoRepository<Users,String> {

    UserDetails findByLogin(String login);

}
