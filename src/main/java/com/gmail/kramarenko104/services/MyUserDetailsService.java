package com.gmail.kramarenko104.services;

import com.gmail.kramarenko104.model.CustomUser;
import com.gmail.kramarenko104.model.User;
import com.gmail.kramarenko104.model.UserGrantedAuthority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class MyUserDetailsService implements UserDetailsService
{
    private static Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private UserService userService;

    private Map<String,String> roles = new HashMap<String,String>();

    public MyUserDetailsService()
    {
        roles.put("ROLE_USER","Customer");
        roles.put("ROLE_ADMIN","Admin");
    }

    public Map<String,String> getRoles()
    {
        return roles;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        logger.debug("[eshop] MyUserDetailsService.loadUserByUsername...enter with username: " + username);
        if (username != null && !username.equals(""))
        {
            User user = userService.getUserByLogin(username);
            if (user == null) {
                    throw new UsernameNotFoundException(username);
            }
            logger.debug("[eshop] MyUserDetailsService.loadUserByUsername...user: " + user);
            GrantedAuthority grantedAuth = new UserGrantedAuthority(user.getRole());
            UserDetails userDetails = new CustomUser(user.getUserId(), user.getLogin(), user.getPassword(),
                    new GrantedAuthority[]{ grantedAuth });
            logger.debug("[eshop] MyUserDetailsService.loadUserByUsername...create userDetails: " + userDetails);
            return userDetails;

        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
