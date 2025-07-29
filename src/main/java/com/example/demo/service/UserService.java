package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.request.UserRequest;

import java.util.List;

public interface UserService {
    User create(UserRequest request);
    List<User> getAll();
}