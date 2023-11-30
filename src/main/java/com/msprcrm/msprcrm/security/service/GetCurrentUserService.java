package com.msprcrm.msprcrm.security.service;

import com.msprcrm.msprcrm.entity.User;
import com.msprcrm.msprcrm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetCurrentUserService {
    @Autowired
    UserRepository userRepository;

    public User getUser() throws ClassCastException{
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<User> optUser = userRepository.findUserByUsername(authentication.getName());
            if (optUser.isEmpty()){
                return null;
            }else {
                return optUser.get();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
