package com.sistemasoee.app.controllers;

import com.sistemasoee.app.entities.User;
import com.sistemasoee.app.repositories.CustomUserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author adan
 * 
 * Implementación del servicio REST que hace de controlador de las entidades.
 *
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"})
public class UserController {

    /**
     * gestión de la traza de la clase
     */
    Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * Repositorio entidades CRUD
     */
    private final CustomUserRepository userRepository;

    public UserController(CustomUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Consulta de los usuarios creados.
     * @return lista de usuarios
     */
    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * Creación/registro de un  nuevo usuario.
     * @param user
     */
    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        userRepository.save(user);
        logger.info("new user: "+user);
    }
    
    /**
     * Valida que un usuario existe y que el password sea correcto.
     * @param user
     * @return mensaje indicando si toso es correcto o la causa del fallo
     */
    @RequestMapping(value = "/userval", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<String> validateUser(@RequestBody User user) { 
    	String validated = "user not registered "+user.getEmail();
    	user.setPassword(md5(user.getPassword()));
    	for(User usr:getUsers()) {
        	if(usr.getEmail().compareToIgnoreCase(user.getEmail())==0) {
        			if(usr.getPassword().compareTo(user.getPassword())==0)        		
        				validated = "validated";
        			else
        				validated = "password incorrect";
        		break;
        	}
        }
    	logger.debug("Validate user: "+user+":"+validated);
        return Collections.singleton(validated);
    }
    
    /**
     * Método estático para calcular el hash md5 de un string. Se emplea para ocultar el password pero poder validar que sea correcto.
     * @param p
     * @return
     */
    public static String md5(String p) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(p.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : result) {
            sb.append(String.format("%02x", b));
        }
    	return sb.toString();
    }
}
