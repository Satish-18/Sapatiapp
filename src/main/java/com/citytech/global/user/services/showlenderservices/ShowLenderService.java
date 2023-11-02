package com.citytech.global.user.services.showlenderservices;

import com.citytech.global.user.repository.Users;
import com.citytech.global.user.services.ApiResponse;

import java.util.List;
import java.util.Optional;

public interface ShowLenderService {
    ApiResponse<List<LenderResponse>> getLenderList();
}
