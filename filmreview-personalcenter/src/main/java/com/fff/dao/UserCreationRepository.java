package com.fff.dao;

import com.fff.domain.User;
import com.fff.domain.UserCreation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCreationRepository extends JpaRepository<UserCreation,Integer> {
}
