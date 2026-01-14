package com.neill.copagolperu.iam.infrastructure.security;

public class AuthCookieConstants {
    private AuthCookieConstants() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }
    public static final String TOKEN_COOKIE_NAME = "auth-token";
    public static final boolean HTTP_ONLY = true;
    public static final boolean COOKIE_SECURE = false;
    public static final int COOKIE_MAX_AGE = 60 * 120;
    public static final String SAME_SITE = "None";
}