package com.fff.dao;

import com.fff.domain.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReviewRepository extends JpaRepository<UserReview,Integer> {
}
