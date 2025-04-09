package com.walking.carpractice.service;

import com.walking.carpractice.domain.Car;
import com.walking.carpractice.domain.Car_;
import com.walking.carpractice.domain.Model;
import com.walking.carpractice.repository.CarRepository;
import org.hibernate.jpa.SpecHints;

import java.util.Map;

public class CarService {
    private final EntityManagerHelper entityManagerHelper;
    private final CarRepository carRepository;

    public CarService(EntityManagerHelper entityManagerHelper, CarRepository carRepository) {
        this.entityManagerHelper = entityManagerHelper;
        this.carRepository = carRepository;
    }

    public Car getById(Long id) {
        return entityManagerHelper.runTransactional(em -> {
            var entityGraph = em.getEntityGraph(Car_.GRAPH_CAR_WITH_OWNERS);
            Map<String, Object> properties = Map.of(SpecHints.HINT_SPEC_LOAD_GRAPH, entityGraph);

            return em.find(Car.class, id, properties);
        });
    }

    public Car create(Car car) {
        return entityManagerHelper.runTransactional(em -> {
            var model = em.find(Model.class, car.getModelId());
            car.setModel(model);

            em.persist(car);

            return car;
        });
    }

    public Car update(Car updated) {
        return entityManagerHelper.runTransactional(em -> {
            var entityGraph = em.getEntityGraph(Car_.GRAPH_CAR_WITH_OWNERS);
            Map<String, Object> properties = Map.of(SpecHints.HINT_SPEC_LOAD_GRAPH, entityGraph);

            var old = em.find(Car.class, updated.getId(), properties);

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

    public void resetTechnicalInspectionByYear(int year) {
        entityManagerHelper.runTransactionalNoResult(em ->
                carRepository.updateSetTechnicalInspectionFalseByYearLessThan(year, em));
    }
}
