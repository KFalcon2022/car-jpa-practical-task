package com.walking.carpractice.converter.brand;

import com.walking.carpractice.converter.AbstractConverter;
import com.walking.carpractice.domain.Brand;
import com.walking.carpractice.model.brand.BrandDto;

import java.time.ZoneOffset;

public class BrandConverter extends AbstractConverter<Brand, BrandDto> {
    @Override
    public BrandDto convert(Brand source) {
        var target = new BrandDto();

        target.setId(source.getId());
        target.setName(source.getName());
        target.setCreated(source.getCreated().atZone(ZoneOffset.UTC));
        target.setUpdated(source.getUpdated().atZone(ZoneOffset.UTC));

        return target;
    }
}
