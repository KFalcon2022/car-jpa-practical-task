package com.walking.carpractice.service;

import com.walking.carpractice.domain.Car;
import com.walking.carpractice.domain.Car_;
import com.walking.carpractice.domain.Model_;
import com.walking.carpractice.domain.User_;
import com.walking.carpractice.model.CarFilter;
import com.walking.carpractice.model.Page;
import com.walking.carpractice.model.Pageable;
import com.walking.carpractice.repository.CarRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;

/**
 * В данном случае решение с Criteria API лишь расширяет существовавшую
 * функциональность сервлета поиска машин (ранее - машин текущего юзера).
 * Но в зависимости от логики решения данный сервис мог быть полезен и для иных сервисов,
 * которые в своей логике так или иначе получают машины по каким-либо критериям.
 */
public class CarSearchService {
    private final EntityManagerHelper entityManagerHelper;
    private final CarRepository carRepository;

    public CarSearchService(EntityManagerHelper entityManagerHelper, CarRepository carRepository) {
        this.entityManagerHelper = entityManagerHelper;
        this.carRepository = carRepository;
    }

    public Page<Car> getByFilter(CarFilter filter, Pageable pageable) {
        return entityManagerHelper.runTransactional(em -> {
            var query = buildQuery(filter, em);

            return carRepository.findBy(query, pageable, em);
        });
    }

    private CriteriaQuery<Car> buildQuery(CarFilter filter, EntityManager em) {
        var builder = em.getCriteriaBuilder();
        var query = builder.createQuery(Car.class);
        var root = query.from(Car.class);

        var predicates = new ArrayList<Predicate>();

        if (!filter.getBrandIds().isEmpty()) {
            var modelJoin = root.join(Car_.model);
            predicates.add(modelJoin.get(Model_.brandId).in(filter.getBrandIds()));
        }

        if (filter.getOwnerId() != null) {
            var userJoin = root.join(Car_.owners);
            predicates.add(builder.equal(userJoin.get(User_.id), filter.getOwnerId()));
        }

        if (filter.getNumberQuery() != null) {
            var numberQuery = "%" + filter.getNumberQuery().toLowerCase() + "%";
            predicates.add(builder.like(builder.lower(root.get(Car_.number)), numberQuery));
        }

        return query.where(predicates.toArray(new Predicate[0]));
    }
}
