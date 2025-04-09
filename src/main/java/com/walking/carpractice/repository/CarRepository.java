package com.walking.carpractice.repository;

import com.walking.carpractice.domain.Car;
import com.walking.carpractice.model.Page;
import com.walking.carpractice.model.Pageable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CarRepository {
    public Page<Car> findBy(CriteriaQuery<Car> criteria, Pageable pageable, EntityManager em) {
        var query = em.createQuery(criteria);

//        Фактически безразмерная страницы
        if (pageable.pageSize() == 0) {
            var result = query.getResultList();

            return new Page<>(result, 0, result.size(), false);
        }

        var result = query.setFirstResult(pageable.offset())
//                Увеличиваем limit на 1. Если страница придет полностью заполненной, значит,
//                существует как минимум один элемент на следующей
//                (относительно базового размера страницы) странице
                .setMaxResults(pageable.pageSize() + 1)
                .getResultList();

        if (result.isEmpty()) {
            return new Page<>(result, pageable.pageNumber(), 0, false);
        }

        var nextExists = result.size() > pageable.pageSize();

        var content = new ArrayList<>(result);
        if (nextExists) {
//            Удаляем "технический" элемент - его достали, только чтобы понять, есть ли следующая страница
            content.removeLast();
        }

        return new Page<>(content, pageable.pageNumber(), content.size(), nextExists);
    }

    public Car findById(Long id, EntityManager em) {
//        Нам все равно понадобится информация о владельцах, как минимум в конвертере. Логично извлечь ее одним запросом
        return em.createQuery("select c from Car c join fetch c.owners where c.id = :id", Car.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Car> findAllByIds(Collection<Long> ids, EntityManager em) {
        return em.createQuery("select c from Car c where c.id in (:ids)", Car.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    public void updateSetTechnicalInspectionFalseByYearLessThan(int year, EntityManager em) {
        em.createQuery("update Car c set c.actualTechnicalInspection = false where year < :year")
                .setParameter("year", year)
                .executeUpdate();
    }
}
