package com.neill.copagolperu.application.mapper;

import com.neill.copagolperu.application.dto.UserInfoDTO;
import com.neill.copagolperu.application.dto.response.LoginResponse;
import com.neill.copagolperu.domain.model.User;

public class UserMapper {
    private UserMapper() {}

    public static UserInfoDTO toUserInfo(User user) {
        if (user == null) {
            return null;
        }

        return new UserInfoDTO(
                user.getId(),
                user.getUsername(),
                user.getRole() != null ? user.getRole().name() : null,
                user.getActivo(),
                user.getAcademia() != null ? user.getAcademia().getId() : null,
                user.getAcademia() != null ? user.getAcademia().getLogoUrl() : null,
                user.getAcademia() != null ? user.getAcademia().getNombreAcademia() : null
        );
    }

    public static LoginResponse toResponse(User user) {
        return new LoginResponse(
                null,
                null,
                0,
                toUserInfo(user)
        );
    }
}