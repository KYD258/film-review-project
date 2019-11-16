package com.fff.service;

import com.fff.commons.GetOrders;
import com.fff.commons.GetReview;
import com.fff.domain.Review;

import java.util.List;

public interface ReviewService {

    //新增评论
    void saveReview(Review review);

    //  根据id删除
    void deleteReviewById(Integer reviewId);

    //  根据id查一条评论
    Review findReviewByReviewId(Integer reviewId);

    //  修改
    Review updateReview(Review review);

    //查询所有评论
    List<Review> findAllReview();

    //  查询该电影的所有评论
    List<GetReview> findReviewByVideoId(Integer videoId);

    //  查询该用户的所有评论
    List<Review> findReviewByUserId(Integer userId);


}
