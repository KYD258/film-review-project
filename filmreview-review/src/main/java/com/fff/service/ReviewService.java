package com.fff.service;

import com.fff.commons.GetOrders;
import com.fff.commons.GetReview;
import com.fff.domain.Review;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReviewService {

    //新增评论
    Review  saveReview(Review review);

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

    //查看该用户是否评论过
    Integer findCount(@Param("videoId") Integer videoId, @Param("userId") Integer userId);

    //找出该用户的评论
    Review findReview(@Param("videoId") Integer videoId, @Param("userId") Integer userId);

}
