package com.fff.Responses;

import com.fff.domain.Video;
import lombok.Data;

import java.util.List;

@Data
public class VideoResponse {
    private List<Video> videoList;
    private Long videoTotal;
}
