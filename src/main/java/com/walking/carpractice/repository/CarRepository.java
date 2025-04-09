package com.walking.carpractice.repository;

import com.walking.carpractice.domain.Car;
import jakarta.persistence.EntityManager;

import java.util.Collection;
import java.util.List;

public class CarRepository {
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

    public List<Car> findAllByUserId(Long userId, EntityManager em) {
        return em.createQuery("select c from Car c join fetch c.owners o where o.id = :userId", Car.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public void updateSetTechnicalInspectionFalseByYearLessThan(int year, EntityManager em) {
        em.createQuery("update Car c set c.actualTechnicalInspection = false where year < :year")
                .setParameter("year", year)
                .executeUpdate();
    }
}
