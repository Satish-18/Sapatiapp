package com.citytech.global.user.services.showuserservices;

import com.citytech.global.user.repository.Users;

import java.util.List;
import java.util.Optional;

public interface ShowUserService {
    List<Users> getAllUsers();
    Optional<Users>getUserById(int uid);
}
