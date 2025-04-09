package com.walking.carpractice.model;

public record Pageable(int pageNumber, int pageSize) {
//    Компактная версия конструктора для рекордов. В общем случае требует явного указания инициализации полей
    public Pageable {
//        Будем считать, что страница с размером 0 - не пагинируемая, то есть запрос всех данных
        if (pageSize < 0) {
            throw new IllegalArgumentException("Размер страницы должен быть не меньше 0");
        }
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Номер страницы должен быть не меньше 0");
        }
    }

    public int offset() {
        return pageNumber * pageSize;
    }
}
