package com.fff.dao;

import com.fff.domain.UserOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrdersRepository extends JpaRepository<UserOrders,Integer> {
}
