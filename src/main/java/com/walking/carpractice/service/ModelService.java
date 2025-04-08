package com.walking.carpractice.service;

import com.walking.carpractice.domain.Brand;
import com.walking.carpractice.domain.Model;
import com.walking.carpractice.repository.ModelRepository;

import java.util.List;

public class ModelService {
    private final EntityManagerHelper entityManagerHelper;
    private final ModelRepository modelRepository;

    public ModelService(EntityManagerHelper entityManagerHelper, ModelRepository modelRepository) {
        this.entityManagerHelper = entityManagerHelper;
        this.modelRepository = modelRepository;
    }

    public Model getById(Long id) {
        return entityManagerHelper.runTransactional(em -> em.find(Model.class, id));
    }

    public List<Model> getAllByBrand(Long brandId) {
        return entityManagerHelper.runTransactional(em -> modelRepository.findAllByBrandId(brandId, em));
    }

    public Model create(Model model) {
        return entityManagerHelper.runTransactional(em -> {
            var brand = em.find(Brand.class, model.getBrandId());
            model.setBrand(brand);

            em.persist(model);

            return model;
        });
    }

    public Model update(Model updated) {
        return entityManagerHelper.runTransactional(em -> {
            var old = em.find(Model.class, updated.getId());

            old.setName(updated.getName());

            return old;
        });
    }

    public void delete(Long id) {
        entityManagerHelper.runTransactionalNoResult(em -> {
            var model = em.find(Model.class, id);
            em.remove(model);
        });
    }
}
