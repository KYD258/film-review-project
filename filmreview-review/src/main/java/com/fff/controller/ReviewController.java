package com.fff.controller;

import com.fff.commons.GetReview;
import com.fff.domain.Review;
import com.fff.domain.User;
import com.fff.domain.Video;
import com.fff.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    //TODO userId获取方式待改
    @RequestMapping("/save")
    public Review saveReview(@RequestBody Review review) {
        Integer userId=2;
        Date date = new Date();
        review.setReviewCreateTime(date);
        Review review1 = reviewService.saveReview(review);
        return review1;

    }

    @RequestMapping("/delete")
    public void deleteReviewById(@RequestBody Review review) {
        reviewService.deleteReviewById(review.getReviewId());
    }

    @RequestMapping("/findByReviewId")
    public Review findReviewByReviewId(@RequestBody Review review) {
        return reviewService.findReviewByReviewId(review.getReviewId());
    }

    @RequestMapping("/update")
    public void updateReview(@RequestBody Review review) {
        reviewService.updateReview(review);
    }

    @RequestMapping("/findAll")
    public List<Review> findAllReview() {
        return reviewService.findAllReview();
    }

    @RequestMapping("/findByVideoId")
    public List<GetReview> findReviewByVideoId(@RequestBody Video video){
        List<GetReview> reviewByVideoId = reviewService.findReviewByVideoId(video.getVideoId());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        for (GetReview getReview : reviewByVideoId) {
//            String format = sdf.format(getReview.getReviewCreateTime());
//            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(format);
//            getReview.setReviewCreateTime(date);
//            System.out.println(getReview);
//        }
//        System.out.println(reviewByVideoId);
        return reviewByVideoId;
    }

    @RequestMapping("/findByUserId")
    //TODO userId获取方式待改
    public List<Review> findReviewByUserId(@RequestBody User user) {
        return reviewService.findReviewByUserId(user.getUserId());
    }

    //TODO userId获取方式待改
    @RequestMapping("/findCount")
    public Review findCount(@RequestBody Review review){
        Integer userId=1;
        Review review1 = reviewService.findReview(review.getVideoId(), userId);
        return review1;
    }
}
