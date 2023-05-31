package com.toritalk.user.service;

import com.toritalk.user.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {
    UserDetails loadUserById(Long userId);
}
