package com.walking.carpractice.service;

import com.walking.carpractice.domain.Car;

public class CarService {

    private final EntityManagerHelper entityManagerHelper;

    public CarService(EntityManagerHelper entityManagerHelper) {
        this.entityManagerHelper = entityManagerHelper;
    }

    public Car getById(Long id) {
        return entityManagerHelper.runTransactional(em -> em.find(Car.class, id));
    }

    public Car create(Car car) {
        return entityManagerHelper.runTransactional(em -> {
            em.persist(car);

            return car;
        });
    }

    public Car update(Car updated) {
        return entityManagerHelper.runTransactional(em -> {
            var old = em.find(Car.class, updated.getId());

            old.setColor(updated.getColor());
            old.setNumber(updated.getNumber());
            old.setActualTechnicalInspection(updated.isActualTechnicalInspection());

            return old;
        });
    }

    public void delete(Long id) {
        entityManagerHelper.runTransactionalNoResult(em -> {
            var car = em.find(Car.class, id);
            em.remove(car);
        });
    }
}
