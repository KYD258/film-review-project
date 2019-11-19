package com.fff.service.impl;

import com.fff.commons.GetOrders;
import com.fff.commons.GetReview;
import com.fff.dao.ReviewMapper;
import com.fff.dao.ReviewRepository;
import com.fff.dao.UserReviewRepository;
import com.fff.domain.Review;
import com.fff.domain.UserReview;
import com.fff.service.ReviewService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private UserReviewRepository userReviewRepository;

    //TODO userId获取方式待改
    @Override
    public Review saveReview(Review review) {
        Integer userId=2;
        Integer reviewId=findCount(review.getVideoId(),userId);
        if(reviewId==null) {
            reviewRepository.save(review);
            UserReview userReview = new UserReview();
            userReview.setReviewId(review.getReviewId());
            userReview.setUserId(userId);
            userReviewRepository.save(userReview);
            return review;
        }else{
            review.setReviewId(reviewId);
            Review review1 = updateReview(review);
            return review1;
        }

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

    //TODO userId获取方式待改
    @Override
    public List<Review> findReviewByUserId(Integer userId) {
        List<Review> byUserId = reviewMapper.findByUserId(userId);
        if(byUserId!=null){
            return byUserId;
        }
        return null;
    }

    //TODO userId获取方式待改
    @Override
    public Integer findCount(@Param("videoId") Integer videoId, @Param("userId") Integer userId) {
        return reviewMapper.findCount(videoId,userId);
    }

    //TODO userId获取方式待改
    @Override
    public Review findReview(@Param("videoId") Integer videoId, @Param("userId") Integer userId) {
        Review review = reviewMapper.findReview(videoId, userId);
        if(review!=null){
            return review;
        }
        return null;
    }
}
