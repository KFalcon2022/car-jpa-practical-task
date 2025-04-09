package com.walking.carpractice.service;

import com.walking.carpractice.domain.Car;
import com.walking.carpractice.domain.User;
import com.walking.carpractice.exception.AuthException;
import com.walking.carpractice.exception.DuplicateUserException;
import com.walking.carpractice.repository.CarRepository;
import com.walking.carpractice.repository.UserRepository;

import java.util.List;

public class UserService {
    private final EncodingService encodingService;

    private final CarRepository carRepository;
    private final UserRepository userRepository;

    private final EntityManagerHelper entityManagerHelper;

    public UserService(EncodingService encodingService, CarRepository carRepository, UserRepository userRepository,
                       EntityManagerHelper entityManagerHelper) {
        this.encodingService = encodingService;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.entityManagerHelper = entityManagerHelper;
    }

    public User getById(Long id) {
        return entityManagerHelper.runTransactional(em -> userRepository.findById(id, em));
    }

    public User auth(String username, String password) {
        var user = entityManagerHelper.runTransactional(em -> userRepository.findByUsername(username, em))
                .orElseThrow(AuthException::new);

        if (!encodingService.match(password, user.getPassword())) {
            throw new AuthException();
        }

        return user;
    }

    public User create(User user) {
        return entityManagerHelper.runTransactional(em -> {
            userRepository.findByUsername(user.getUsername(), em)
                    .ifPresent(u -> {
                        throw new DuplicateUserException();
                    });

            var encodedPassword = encodingService.encode(user.getPassword());
            user.setPassword(encodedPassword);

            em.persist(user);

            return user;
        });
    }

    public User update(User updated, List<Long> carIds) {
        return entityManagerHelper.runTransactional(em -> {
            var old = userRepository.findById(updated.getId(), em);

            old.setFirstName(updated.getFirstName());
            old.setLastName(updated.getLastName());

            List<Car> cars = carIds.isEmpty()
                    ? List.of()
                    : carRepository.findAllByIds(carIds, em);

            old.getCars().clear();
            old.getCars().addAll(cars);

            return old;
        });
    }
}
