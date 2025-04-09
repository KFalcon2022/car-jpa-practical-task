package com.walking.carpractice.converter;

import com.walking.carpractice.model.Page;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<S, T> {
    public abstract T convert(S source);

    public List<T> convert(List<S> list) {
        return list.stream()
                .map(this::convert)
                .toList();
    }

    public Page<T> convert(Page<S> page) {
        return page.content()
                .stream()
                .map(this::convert)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        content -> new Page<>(content, page.number(), page.size(), page.nextExists())
                ));
    }
}
