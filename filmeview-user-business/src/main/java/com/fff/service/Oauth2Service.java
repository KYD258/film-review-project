package com.fff.service;

public interface Oauth2Service {

    String AliLogin(String code);

    String GitLogin(String code);
}
