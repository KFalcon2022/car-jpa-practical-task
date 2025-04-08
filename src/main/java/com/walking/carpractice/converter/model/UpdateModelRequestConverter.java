package com.walking.carpractice.converter.model;

import com.walking.carpractice.converter.AbstractConverter;
import com.walking.carpractice.domain.Model;
import com.walking.carpractice.model.model.request.UpdateModelRequest;

public class UpdateModelRequestConverter extends AbstractConverter<UpdateModelRequest, Model> {
    @Override
    public Model convert(UpdateModelRequest source) {
        var target = new Model();

        target.setId(source.getId());
        target.setName(source.getName());

        return target;
    }
}
