package com.walking.carpractice.converter;

import com.walking.carpractice.domain.Car;
import com.walking.carpractice.model.request.CreateCarRequest;

public class CreateCarRequestConverter extends AbstractConverter<CreateCarRequest, Car> {
    @Override
    public Car convert(CreateCarRequest source) {
        var car = new Car();

        car.setNumber(source.getNumber());
        car.setYear(source.getYear());
        car.setColor(source.getColor());
        car.setActualTechnicalInspection(source.isActualTechnicalInspection());

        return car;
    }
}
