package com.walking.carpractice.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(User.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class User_ {

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String CARS = "cars";
	public static final String PASSWORD = "password";
	public static final String CREATED = "created";
	public static final String ID = "id";
	public static final String UPDATED = "updated";
	public static final String USERNAME = "username";

	
	/**
	 * @see com.walking.carpractice.domain.User#firstName
	 **/
	public static volatile SingularAttribute<User, String> firstName;
	
	/**
	 * @see com.walking.carpractice.domain.User#lastName
	 **/
	public static volatile SingularAttribute<User, String> lastName;
	
	/**
	 * @see com.walking.carpractice.domain.User#cars
	 **/
	public static volatile ListAttribute<User, Car> cars;
	
	/**
	 * @see com.walking.carpractice.domain.User#password
	 **/
	public static volatile SingularAttribute<User, String> password;
	
	/**
	 * @see com.walking.carpractice.domain.User#created
	 **/
	public static volatile SingularAttribute<User, LocalDateTime> created;
	
	/**
	 * @see com.walking.carpractice.domain.User#id
	 **/
	public static volatile SingularAttribute<User, Long> id;
	
	/**
	 * @see com.walking.carpractice.domain.User
	 **/
	public static volatile EntityType<User> class_;
	
	/**
	 * @see com.walking.carpractice.domain.User#updated
	 **/
	public static volatile SingularAttribute<User, LocalDateTime> updated;
	
	/**
	 * @see com.walking.carpractice.domain.User#username
	 **/
	public static volatile SingularAttribute<User, String> username;

}

