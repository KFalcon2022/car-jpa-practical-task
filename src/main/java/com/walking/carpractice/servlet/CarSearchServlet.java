package com.walking.carpractice.servlet;

import com.walking.carpractice.constant.ContextAttributeNames;
import com.walking.carpractice.converter.car.CarConverter;
import com.walking.carpractice.model.CarFilter;
import com.walking.carpractice.model.Pageable;
import com.walking.carpractice.service.CarSearchService;
import com.walking.carpractice.servlet.filter.ResponseJsonSerializerFilter;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;

public class CarSearchServlet extends HttpServlet {
    private CarSearchService carService;
    private CarConverter carConverter;

    @Override
    public void init(ServletConfig config) throws ServletException {
        var servletContext = config.getServletContext();

        this.carService = (CarSearchService) servletContext.getAttribute(ContextAttributeNames.CAR_SEARCH_SERVICE);
        this.carConverter = (CarConverter) servletContext.getAttribute(ContextAttributeNames.CAR_CONVERTER);
    }

    /**
     * Получение машин по фильтру: частичному совпадению номера,
     * принадлежности одной из марок или конкретному пользователю
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        var filter = getFilter(request);
        var pageable = getPageable(request);

        var cars = carService.getByFilter(filter, pageable);
        var carDtos = carConverter.convert(cars);

        request.setAttribute(ResponseJsonSerializerFilter.POJO_RESPONSE_BODY, carDtos);
    }

    private CarFilter getFilter(HttpServletRequest request) {
        var userId = Optional.ofNullable(request.getParameter("userId"))
                .map(Long::parseLong)
                .orElse(null);
        var numberQuery = request.getParameter("numberQuery");
        var brandIds = Optional.ofNullable(request.getParameterValues("brandIds"))
                .stream()
                .map(List::of)
                .flatMap(List::stream)
                .map(Long::parseLong)
                .toList();

        var filter = new CarFilter();
        filter.setBrandIds(brandIds);
        filter.setNumberQuery(numberQuery);
        filter.setOwnerId(userId);

        return filter;
    }

    private Pageable getPageable(HttpServletRequest request) {
        var size = Optional.ofNullable(request.getParameter("pageSize"))
                .map(Integer::parseInt)
                .orElse(0);
        var number = Optional.ofNullable(request.getParameter("pageNumber"))
                .map(Integer::parseInt)
                .orElse(0);

        return new Pageable(number, size);
    }
}
