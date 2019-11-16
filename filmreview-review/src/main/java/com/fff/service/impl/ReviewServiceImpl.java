package com.fff.service.impl;

import com.fff.commons.GetOrders;
import com.fff.commons.GetReview;
import com.fff.dao.ReviewMapper;
import com.fff.dao.ReviewRepository;
import com.fff.domain.Review;
import com.fff.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewMapper reviewMapper;


    @Override
    public void saveReview(Review review) {
         reviewRepository.save(review);
    }

    @Override
    public void deleteReviewById(Integer reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public Review findReviewByReviewId(Integer reviewId) {
        Review review = reviewRepository.findById(reviewId).get();
        if(review!=null){
            return review;
        }
        return null;
    }

    @Override
    public Review updateReview(Review review) {
        Review review1 = reviewRepository.saveAndFlush(review);
        return review1;
    }

    @Override
    public List<Review> findAllReview() {
        return reviewRepository.findAll();
    }

    @Override
    public List<GetReview> findReviewByVideoId(Integer videoId) {
        List<GetReview> byVideoId = reviewMapper.findByVideoId(videoId);
        if(byVideoId!=null){
            return byVideoId;
        }
        return null;
    }

    @Override
    public List<Review> findReviewByUserId(Integer userId) {
        List<Review> byUserId = reviewMapper.findByUserId(userId);
        if(byUserId!=null){
            return byUserId;
        }
        return null;
    }
}
