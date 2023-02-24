package com.example.getmanapp.utils.mix;

import org.springframework.http.HttpMethod;

import java.util.Set;

public final class Constants {
    public static final Set<HttpMethod> HTTP_METHODS_WITHOUT_BODY =
            Set.of(HttpMethod.HEAD, HttpMethod.GET, HttpMethod.DELETE);
    public static final Set<HttpMethod> HTTP_METHODS_HAVING_BODY =
            Set.of(HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.TRACE, HttpMethod.OPTIONS);
}
