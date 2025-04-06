package com.walking.carpractice.converter;

import com.walking.carpractice.domain.Car;
import com.walking.carpractice.model.request.UpdateCarRequest;

public class UpdateCarRequestConverter extends AbstractConverter<UpdateCarRequest, Car> {
    @Override
    public Car convert(UpdateCarRequest source) {
        var car = new Car();

        car.setId(source.getId());
        car.setNumber(source.getNumber());
        car.setColor(source.getColor());
        car.setActualTechnicalInspection(source.isActualTechnicalInspection());

        return car;
    }
}
