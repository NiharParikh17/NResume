package com.nhparikh.resume.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegexFactory {
    public static final String EMAIL_REGEX="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}$";
    public static final String PHONE_REGEX="^(\\(?\\+?[0-9]*\\)?)?[0-9_\\- \\(\\)]*$";
}
