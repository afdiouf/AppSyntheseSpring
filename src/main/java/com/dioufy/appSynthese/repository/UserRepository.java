package com.dioufy.appSynthese.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dioufy.appSynthese.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
