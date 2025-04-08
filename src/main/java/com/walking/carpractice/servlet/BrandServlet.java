package com.walking.carpractice.servlet;

import com.walking.carpractice.constant.ContextAttributeNames;
import com.walking.carpractice.converter.brand.BrandConverter;
import com.walking.carpractice.converter.brand.CreateBrandRequestConverter;
import com.walking.carpractice.converter.brand.UpdateBrandRequestConverter;
import com.walking.carpractice.model.brand.request.CreateBrandRequest;
import com.walking.carpractice.model.brand.request.UpdateBrandRequest;
import com.walking.carpractice.service.BrandService;
import com.walking.carpractice.servlet.filter.RequestJsonDeserializerFilter;
import com.walking.carpractice.servlet.filter.ResponseJsonSerializerFilter;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BrandServlet extends HttpServlet {
    private BrandService brandService;

    private BrandConverter brandConverter;
    private CreateBrandRequestConverter createBrandRequestConverter;
    private UpdateBrandRequestConverter updateBrandRequestConverter;

    @Override
    public void init(ServletConfig config) {
        var servletContext = config.getServletContext();

        this.brandService = (BrandService) servletContext.getAttribute(ContextAttributeNames.BRAND_SERVICE);
        this.brandConverter = (BrandConverter) servletContext.getAttribute(ContextAttributeNames.BRAND_CONVERTER);

        this.createBrandRequestConverter = (CreateBrandRequestConverter) servletContext.getAttribute(
                ContextAttributeNames.CREATE_BRAND_REQUEST_CONVERTER);

        this.updateBrandRequestConverter = (UpdateBrandRequestConverter) servletContext.getAttribute(
                ContextAttributeNames.UPDATE_BRAND_REQUEST_CONVERTER);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        var id = Long.valueOf(request.getParameter("id"));

        var brand = brandService.getById(id);
        var BrandDto = brandConverter.convert(brand);

        request.setAttribute(ResponseJsonSerializerFilter.POJO_RESPONSE_BODY, BrandDto);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        var brandRequest = (CreateBrandRequest) request.getAttribute(RequestJsonDeserializerFilter.POJO_REQUEST_BODY);
        var brand = createBrandRequestConverter.convert(brandRequest);

        var createdBrand = brandService.create(brand);
        var brandDto = brandConverter.convert(createdBrand);

        request.setAttribute(ResponseJsonSerializerFilter.POJO_RESPONSE_BODY, brandDto);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        var brandRequest = (UpdateBrandRequest) request.getAttribute(RequestJsonDeserializerFilter.POJO_REQUEST_BODY);
        var brand = updateBrandRequestConverter.convert(brandRequest);

        var updatedBrand = brandService.update(brand);
        var brandDto = brandConverter.convert(updatedBrand);

        request.setAttribute(ResponseJsonSerializerFilter.POJO_RESPONSE_BODY, brandDto);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        var id = Long.valueOf(request.getParameter("id"));

        brandService.delete(id);
    }
}
