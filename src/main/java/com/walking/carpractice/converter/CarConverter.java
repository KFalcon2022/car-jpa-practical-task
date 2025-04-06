package com.walking.carpractice.converter;

import com.walking.carpractice.domain.Car;
import com.walking.carpractice.model.CarDto;

import java.time.ZoneOffset;

public class CarConverter extends AbstractConverter<Car, CarDto> {
    @Override
    public CarDto convert(Car source) {
        var carDto = new CarDto();

        carDto.setId(source.getId());
        carDto.setNumber(source.getNumber());
        carDto.setYear(source.getYear());
        carDto.setColor(source.getColor());
        carDto.setActualTechnicalInspection(source.isActualTechnicalInspection());
        carDto.setCreated(source.getCreated().atZone(ZoneOffset.UTC));
        carDto.setUpdated(source.getUpdated().atZone(ZoneOffset.UTC));

        return carDto;
    }
}
