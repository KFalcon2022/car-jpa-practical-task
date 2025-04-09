package com.walking.carpractice.converter.user;

import com.walking.carpractice.converter.AbstractConverter;
import com.walking.carpractice.domain.User;
import com.walking.carpractice.model.dto.user.request.CreateUserRequest;

public class CreateUserRequestConverter extends AbstractConverter<CreateUserRequest, User> {
    @Override
    public User convert(CreateUserRequest source) {
        var user = new User();

        user.setFirstName(source.getFirstName());
        user.setLastName(source.getLastName());
        user.setUsername(source.getUsername());
        user.setPassword(source.getPassword());

        return user;
    }
}
