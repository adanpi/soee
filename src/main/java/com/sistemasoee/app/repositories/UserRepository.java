package com.sistemasoee.app.repositories;


import com.sistemasoee.app.entities.User;
import org.springframework.data.repository.CrudRepository;


/**
 * @author adan
 * 
 * Interfaz de gestión de entidades CRUD.
 * Implementada en {@link CustomUserRepository CustomUserRepository.class} para sobreescribir método save.
 *
 */
public interface UserRepository extends CrudRepository<User, Long>{


}
