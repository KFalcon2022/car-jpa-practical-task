package com.walking.carpractice.converter.user;

import com.walking.carpractice.converter.AbstractConverter;
import com.walking.carpractice.domain.Car;
import com.walking.carpractice.domain.User;
import com.walking.carpractice.model.dto.user.UserDto;

import java.time.ZoneOffset;

public class UserConverter extends AbstractConverter<User, UserDto> {
    @Override
    public UserDto convert(User source) {
        var target = new UserDto();

        target.setId(source.getId());
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setUsername(source.getUsername());

        var carIds = source.getCars()
                .stream()
                .map(Car::getId)
                .toList();
        target.setCarIds(carIds);

        target.setCreated(source.getCreated().atZone(ZoneOffset.UTC));
        target.setUpdated(source.getUpdated().atZone(ZoneOffset.UTC));

        return target;
    }
}
