package com.project.dao;

import java.util.List;

import com.project.model.UserProfile;

public interface UserProfileDao {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}
