package com.walking.carpractice.converter.brand;

import com.walking.carpractice.converter.AbstractConverter;
import com.walking.carpractice.domain.Brand;
import com.walking.carpractice.model.brand.request.CreateBrandRequest;

public class CreateBrandRequestConverter extends AbstractConverter<CreateBrandRequest, Brand> {
    @Override
    public Brand convert(CreateBrandRequest source) {
        var target = new Brand();

        target.setName(source.getName());

        return target;
    }
}
