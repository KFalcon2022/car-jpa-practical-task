package com.walking.carpractice.converter.car;

import com.walking.carpractice.converter.AbstractConverter;
import com.walking.carpractice.domain.Car;
import com.walking.carpractice.domain.User;
import com.walking.carpractice.model.dto.car.CarDto;

import java.time.ZoneOffset;

public class CarConverter extends AbstractConverter<Car, CarDto> {
    @Override
    public CarDto convert(Car source) {
        var target = new CarDto();

        target.setId(source.getId());
        target.setNumber(source.getNumber());
        target.setYear(source.getYear());
        target.setColor(source.getColor());
        target.setActualTechnicalInspection(source.isActualTechnicalInspection());

        var ownerIds = source.getOwners()
                .stream()
                .map(User::getId)
                .toList();
        target.setOwnerIds(ownerIds);

        target.setCreated(source.getCreated().atZone(ZoneOffset.UTC));
        target.setUpdated(source.getUpdated().atZone(ZoneOffset.UTC));

        return target;
    }
}
