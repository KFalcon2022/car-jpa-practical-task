package com.walking.carpractice.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(Model.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Model_ {

	public static final String CREATED = "created";
	public static final String BRAND_ID = "brandId";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String BRAND = "brand";
	public static final String UPDATED = "updated";

	
	/**
	 * @see com.walking.carpractice.domain.Model#created
	 **/
	public static volatile SingularAttribute<Model, LocalDateTime> created;
	
	/**
	 * @see com.walking.carpractice.domain.Model#brandId
	 **/
	public static volatile SingularAttribute<Model, Long> brandId;
	
	/**
	 * @see com.walking.carpractice.domain.Model#name
	 **/
	public static volatile SingularAttribute<Model, String> name;
	
	/**
	 * @see com.walking.carpractice.domain.Model#id
	 **/
	public static volatile SingularAttribute<Model, Long> id;
	
	/**
	 * @see com.walking.carpractice.domain.Model
	 **/
	public static volatile EntityType<Model> class_;
	
	/**
	 * @see com.walking.carpractice.domain.Model#brand
	 **/
	public static volatile SingularAttribute<Model, Brand> brand;
	
	/**
	 * @see com.walking.carpractice.domain.Model#updated
	 **/
	public static volatile SingularAttribute<Model, LocalDateTime> updated;

}

