package com.neill.copagolperu.application.dto.response;

import com.neill.copagolperu.application.dto.UserInfoDTO;

public record LoginResponse(
        String token,
        String type,
        long expiresIn,
        UserInfoDTO user
) {}