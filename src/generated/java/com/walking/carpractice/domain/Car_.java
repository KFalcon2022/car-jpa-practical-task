package com.walking.carpractice.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(Car.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Car_ {

	public static final String NUMBER = "number";
	public static final String COLOR = "color";
	public static final String YEAR = "year";
	public static final String ACTUAL_TECHNICAL_INSPECTION = "actualTechnicalInspection";
	public static final String MODEL_ID = "modelId";
	public static final String GRAPH_CAR_WITH_OWNERS = "car-with-owners";
	public static final String CREATED = "created";
	public static final String OWNERS = "owners";
	public static final String MODEL = "model";
	public static final String ID = "id";
	public static final String UPDATED = "updated";

	
	/**
	 * @see com.walking.carpractice.domain.Car#number
	 **/
	public static volatile SingularAttribute<Car, String> number;
	
	/**
	 * @see com.walking.carpractice.domain.Car#color
	 **/
	public static volatile SingularAttribute<Car, String> color;
	
	/**
	 * @see com.walking.carpractice.domain.Car#year
	 **/
	public static volatile SingularAttribute<Car, Integer> year;
	
	/**
	 * @see com.walking.carpractice.domain.Car#actualTechnicalInspection
	 **/
	public static volatile SingularAttribute<Car, Boolean> actualTechnicalInspection;
	
	/**
	 * @see com.walking.carpractice.domain.Car#modelId
	 **/
	public static volatile SingularAttribute<Car, Long> modelId;
	
	/**
	 * @see com.walking.carpractice.domain.Car#created
	 **/
	public static volatile SingularAttribute<Car, LocalDateTime> created;
	
	/**
	 * @see com.walking.carpractice.domain.Car#owners
	 **/
	public static volatile ListAttribute<Car, User> owners;
	
	/**
	 * @see com.walking.carpractice.domain.Car#model
	 **/
	public static volatile SingularAttribute<Car, Model> model;
	
	/**
	 * @see com.walking.carpractice.domain.Car#id
	 **/
	public static volatile SingularAttribute<Car, Long> id;
	
	/**
	 * @see com.walking.carpractice.domain.Car
	 **/
	public static volatile EntityType<Car> class_;
	
	/**
	 * @see com.walking.carpractice.domain.Car#updated
	 **/
	public static volatile SingularAttribute<Car, LocalDateTime> updated;

}

