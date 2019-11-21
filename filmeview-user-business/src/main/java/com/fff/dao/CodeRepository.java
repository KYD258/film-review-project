package com.fff.dao;

import com.fff.domain.Code;
import com.fff.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code,Integer> {
}
