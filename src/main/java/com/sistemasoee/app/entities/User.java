package com.sistemasoee.app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author adan
 * 
 * Entidad JPA
 *
 */
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    private Integer age;
    private String password;
    
    public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
        this.name = "";
        this.email = "";
        this.age = -1;
        this.password = "";
    }
    
    public User(String name, String email, Integer age, String password) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    public Integer getAge() {
		return age;
	}

	public String getPassword() {
		return password;
	}

	public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", md5=" + password + '}';
    }
}
