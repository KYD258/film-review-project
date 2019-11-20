package com.fff.dao;

import com.fff.Responses.VideoResponse;
import com.fff.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video,Integer> {
    int deleteByVideoId(Integer id);
    List<Video> findVideoByShowIndex(Integer showIndex);
    @Query(value = "SELECT * from video  ORDER BY video_show_time DESC LIMIT ?1,?2 ",nativeQuery = true)
    List<Video> findAllByShowTimeByPage(Integer index,Integer size);
    @Query(value = "SELECT * from video  ORDER BY video_grade DESC LIMIT ?1,?2 ",nativeQuery = true)
    List<Video> findAllByGradeByPage(Integer index,Integer size);

}
