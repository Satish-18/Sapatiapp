package com.citytech.global.user.services.showborrowerservices;

import com.citytech.global.user.repository.Users;

import java.util.Optional;

public interface ShowBorrowerService {
    Optional<Users> showAllBorrowers();
}
