package com.walking.carpractice.converter.model;

import com.walking.carpractice.converter.AbstractConverter;
import com.walking.carpractice.domain.Model;
import com.walking.carpractice.model.model.ModelDto;

import java.time.ZoneOffset;

public class ModelConverter extends AbstractConverter<Model, ModelDto> {
    @Override
    public ModelDto convert(Model source) {
        var target = new ModelDto();

        target.setId(source.getId());
        target.setName(source.getName());
        target.setBrandId(source.getBrandId());
        target.setCreated(source.getCreated().atZone(ZoneOffset.UTC));
        target.setUpdated(source.getUpdated().atZone(ZoneOffset.UTC));

        return target;
    }
}
