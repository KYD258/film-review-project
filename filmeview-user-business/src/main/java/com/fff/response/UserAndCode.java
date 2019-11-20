package com.fff.response;

import com.fff.domain.User;
import lombok.Data;

@Data
public class UserAndCode {
    private User user;
    private Integer code;
}
