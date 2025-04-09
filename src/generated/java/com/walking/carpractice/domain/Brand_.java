package com.walking.carpractice.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(Brand.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Brand_ {

	public static final String MODELS = "models";
	public static final String CREATED = "created";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String UPDATED = "updated";

	
	/**
	 * @see com.walking.carpractice.domain.Brand#models
	 **/
	public static volatile ListAttribute<Brand, Model> models;
	
	/**
	 * @see com.walking.carpractice.domain.Brand#created
	 **/
	public static volatile SingularAttribute<Brand, LocalDateTime> created;
	
	/**
	 * @see com.walking.carpractice.domain.Brand#name
	 **/
	public static volatile SingularAttribute<Brand, String> name;
	
	/**
	 * @see com.walking.carpractice.domain.Brand#id
	 **/
	public static volatile SingularAttribute<Brand, Long> id;
	
	/**
	 * @see com.walking.carpractice.domain.Brand
	 **/
	public static volatile EntityType<Brand> class_;
	
	/**
	 * @see com.walking.carpractice.domain.Brand#updated
	 **/
	public static volatile SingularAttribute<Brand, LocalDateTime> updated;

}

