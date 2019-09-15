package com.gmail.kramarenko104.model;

import com.gmail.kramarenko104.services.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;

public class UserGrantedAuthority implements GrantedAuthority
{
    private static Logger logger = LoggerFactory.getLogger(UserGrantedAuthority.class);
    private String authority = null;

    public UserGrantedAuthority(String auth) { authority = auth; }

    @Override
    public String getAuthority() {
        logger.info("[eshop] UserGrantedAuthority.getAuthority...return with authority: " + authority);
        return authority;
    }
}