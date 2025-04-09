package com.walking.carpractice.converter.model;

import com.walking.carpractice.converter.AbstractConverter;
import com.walking.carpractice.domain.Model;
import com.walking.carpractice.model.dto.model.request.CreateModelRequest;

public class CreateModelRequestConverter extends AbstractConverter<CreateModelRequest, Model> {
    @Override
    public Model convert(CreateModelRequest source) {
        var target = new Model();

        target.setName(source.getName());
        target.setBrandId(source.getBrandId());

        return target;
    }
}
