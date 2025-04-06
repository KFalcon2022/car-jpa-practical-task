package com.walking.carpractice.servlet.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.walking.carpractice.constant.ContextAttributeNames;
import com.walking.carpractice.converter.CarConverter;
import com.walking.carpractice.converter.CreateCarRequestConverter;
import com.walking.carpractice.converter.UpdateCarRequestConverter;
import com.walking.carpractice.service.CarService;
import com.walking.carpractice.service.EntityManagerHelper;
import com.walking.carpractice.service.MigrationService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;

public class AddAttributesContextListener implements ServletContextListener {
    private static final Logger log = LogManager.getLogger(AddAttributesContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        log.info("Запущена инициализация атрибутов глобального контекста");

        var servletContext = event.getServletContext();

        var carConverter = new CarConverter();
        servletContext.setAttribute(ContextAttributeNames.CAR_CONVERTER, carConverter);

        var createCarRequestConverter = new CreateCarRequestConverter();
        servletContext.setAttribute(ContextAttributeNames.CREATE_CAR_REQUEST_CONVERTER, createCarRequestConverter);

        var updateCarRequestConverter = new UpdateCarRequestConverter();
        servletContext.setAttribute(ContextAttributeNames.UPDATE_CAR_REQUEST_CONVERTER, updateCarRequestConverter);

        var entityManagerFactory = Persistence.createEntityManagerFactory("Hibernate");
        servletContext.setAttribute(ContextAttributeNames.ENTITY_MANAGER_FACTORY, entityManagerFactory);

        var entityManagerHelper = new EntityManagerHelper(entityManagerFactory);
        servletContext.setAttribute(ContextAttributeNames.ENTITY_MANAGER_HELPER, entityManagerHelper);

        var carService = new CarService(entityManagerHelper);
        servletContext.setAttribute(ContextAttributeNames.CAR_SERVICE, carService);

        var dataSource = getDataSource(entityManagerFactory);
        var migrationService = new MigrationService(dataSource);
        servletContext.setAttribute(ContextAttributeNames.MIGRATION_SERVICE, migrationService);

        var objectMapper = getObjectMapper();
        servletContext.setAttribute(ContextAttributeNames.OBJECT_MAPPER, objectMapper);

        log.info("Завершена инициализация атрибутов глобального контекста");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        var entityManagerFactory = (EntityManagerFactory) sce.getServletContext()
                .getAttribute(ContextAttributeNames.ENTITY_MANAGER_FACTORY);

        entityManagerFactory.close();
    }

    private ObjectMapper getObjectMapper() {
        var objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule())
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        return objectMapper;
    }

    //    Получение объекта датасорса в JPA - нетривиальная задача.
    //    Чтобы не создавать отдельный датасорс только ради миграций - придется повозиться
    private DataSource getDataSource(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.unwrap(SessionFactoryServiceRegistry.class)
                .getService(ConnectionProvider.class)
                .unwrap(DataSource.class);
    }
}
