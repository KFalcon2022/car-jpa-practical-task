package com.walking.carpractice.repository;

import com.walking.carpractice.domain.Car;
import jakarta.persistence.EntityManager;

import java.util.Collection;
import java.util.List;

@SuppressWarnings("unchecked")
public class CarRepository {
    public List<Car> findAllByIds(Collection<Long> ids, EntityManager em) {
        return em.createNativeQuery("select * from car where id in (:ids)", Car.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    public List<Car> findAllByUserId(Long userId, EntityManager em) {
        var sql = """
                select c.* from car c
                join user_car uc on c.id = uc.fk_car
                join "user" u on uc.fk_user = u.id
                where u.id = :userId
                """;

        return em.createNativeQuery(sql, Car.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
