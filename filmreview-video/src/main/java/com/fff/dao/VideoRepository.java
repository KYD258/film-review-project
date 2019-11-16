package com.fff.dao;

import com.fff.Responses.VideoResponse;
import com.fff.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video,Integer> {
    int deleteByVideoId(Integer id);
    List<Video> findVideoByShowIndex(Integer showIndex);
}
