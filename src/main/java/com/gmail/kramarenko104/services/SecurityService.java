package com.gmail.kramarenko104.services;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
