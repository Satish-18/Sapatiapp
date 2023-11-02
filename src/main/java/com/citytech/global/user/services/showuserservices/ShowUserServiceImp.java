package com.citytech.global.user.services.showuserservices;

import com.citytech.global.user.repository.UserRepository;
import com.citytech.global.user.repository.Users;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class ShowUserServiceImp implements ShowUserService {

    private final UserRepository userRepository;
    @Inject
    public ShowUserServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> getUserById(int uid) {
        return userRepository.findById(uid);
    }
}
