package com.walking.carpractice.service;

import com.walking.carpractice.domain.Brand;

public class BrandService {
    private final EntityManagerHelper entityManagerHelper;

    public BrandService(EntityManagerHelper entityManagerHelper) {
        this.entityManagerHelper = entityManagerHelper;
    }

    public Brand getById(Long id) {
        return entityManagerHelper.runTransactional(em -> em.find(Brand.class, id));
    }

    public Brand create(Brand brand) {
        return entityManagerHelper.runTransactional(em -> {
            em.persist(brand);

            return brand;
        });
    }

    public Brand update(Brand updated) {
        return entityManagerHelper.runTransactional(em -> {
            var old = em.find(Brand.class, updated.getId());

            old.setName(updated.getName());

            return old;
        });
    }

    public void delete(Long id) {
        entityManagerHelper.runTransactionalNoResult(em -> {
            var brand = em.find(Brand.class, id);
            em.remove(brand);
        });
    }
}
