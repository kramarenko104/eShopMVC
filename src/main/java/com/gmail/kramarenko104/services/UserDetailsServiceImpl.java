package com.gmail.kramarenko104.services;

import com.gmail.kramarenko104.model.Role;
import com.gmail.kramarenko104.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService
{
    private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        logger.debug("!!! julia [eshop] UserDetailsServiceImpl.loadUserByUsername...enter with username: " + username);
        Thread.dumpStack();;
        if (username != null && !username.equals(""))
        {
            User user = userService.getUserByLogin(username);
            if (user == null) {
                    throw new UsernameNotFoundException(username);
            }
            logger.debug("[eshop] UserDetailsServiceImpl.loadUserByUsername...user: " + user);

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (Role role : user.getRoles()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }

            UserDetails userDetails =
                    new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
            logger.debug("[eshop] UserDetailsServiceImpl.loadUserByUsername...create userDetails: " + userDetails);
            return userDetails;

        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
