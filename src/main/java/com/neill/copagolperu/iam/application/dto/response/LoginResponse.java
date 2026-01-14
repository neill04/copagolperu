package com.neill.copagolperu.iam.application.dto.response;

public record LoginResponse(
        String token,
        String type,
        long expiresIn,
        UserInfoDTO user
) {}