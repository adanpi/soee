package com.sistemasoee.app.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sistemasoee.app.controllers.UserController;
import com.sistemasoee.app.entities.User;

/**
 * @author adan
 * 
 * Gestión CRUD entidades {@link User User.class}.
 * Sobreescribe método save para guardar md5 hash del password en lugar del propio password.
 * El resto de métodos delegan en {@link org.springframework.data.repository.CrudRepository CrudRepository}.
 *
 */
@Repository
public class CustomUserRepository implements UserRepository{

    @Autowired
    @Qualifier("userRepository") // inject Spring implementation here
    private UserRepository userRepository;
    
	
	/**
	 * Sobreescribe método save para guardar md5 hash del password en lugar del propio password.
	 */
	public User save(User user) {
		user.setPassword(UserController.md5(user.getPassword()));
		return userRepository.save(user);
	}


	@Override
	public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {

		return userRepository.saveAll(entities);
	}


	@Override
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean existsById(Long id) {

		return userRepository.existsById(id);
	}


	@Override
	public Iterable<User> findAll() {

		return userRepository.findAll();
	}


	@Override
	public Iterable<User> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public long count() {

		return userRepository.count();
	}


	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
		
	}


	@Override
	public void delete(User entity) {
		userRepository.delete(entity);
		
	}


	@Override
	public void deleteAll(Iterable<? extends User> entities) {
		userRepository.deleteAll(entities);
		
	}


	@Override
	public void deleteAll() {
		
		userRepository.deleteAll();
		
	}
}
