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

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @RequestMapping("/save")
    public String saveReview(@RequestBody Review review) {
        reviewService.saveReview(review);
        return "新增成功";
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
    public List<GetReview> findReviewByVideoId(@RequestBody Video video) {
        return reviewService.findReviewByVideoId(video.getVideoId());
    }

    @RequestMapping("/findByUserId")
    //TODO userId获取方式待改
    public List<Review> findReviewByUserId(@RequestBody User user) {
        return reviewService.findReviewByUserId(user.getUserId());
    }
}
