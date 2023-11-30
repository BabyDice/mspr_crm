package com.msprcrm.msprcrm.security.service;

import com.msprcrm.msprcrm.entity.User;
import com.msprcrm.msprcrm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(pseudo)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur inconnu"));
        return UserDetailsImpl.build(user);
    }
}