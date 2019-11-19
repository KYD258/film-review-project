package com.fff.Responses;

import com.fff.domain.Video;
import lombok.Data;

import java.util.List;

@Data
public class VideoResponse<T> {
    private List<T> videoList;
    private Long videoTotal;
}
