package com.sistemasoee.app;

import com.sistemasoee.app.entities.User;
import com.sistemasoee.app.repositories.CustomUserRepository;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author adan
 * 
 * Lanza Spring Boot. Se permiten parámetros para iniciar la base de datos y/o usar base de datos en fichero o memoria volátil.
 * <br>
 * # lanzar back-end sin parámetros
 * <br>
 * mvn spring-boot:run 
 * <br>
 * # lanzar con argumentos, prevalecen sobre el contenido de application.properties
 * <br>
 * mvn spring-boot:run -Dspring-boot.run.arguments="--initbd --logging.level.com.sistemasoee=DEBUG --spring.datasource.url=jdbc:h2:mem:soee;DB_CLOSE_DELAY=-1"  
 * <br>
 * # propiedades para ajustar Spring: en src/main/resources/application.properties
 * <br>
 * https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html
 * <br>
 *
 */
@SpringBootApplication
public class Application {

	static Logger logger = LoggerFactory.getLogger(Application.class);
	/**
	 * Si se establece a true se inicia la base de datos con usuarios de prueba.<br>
	 * Se puede pasar como parámetro de aplicación.
	 */
	static boolean init_bd = false;

	public static void main(String[] args) {
		int i=0;
		for(String arg:args) {
			logger.debug((i++)+":"+arg);
			if(arg.compareTo("--initbd")==0) {
				init_bd = true;
				logger.info("iniciar bd");
			}
		}
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init(CustomUserRepository userRepository) {
		return args -> {
			if(init_bd)
				Stream.of("Juan", "Perico", "Andrés").forEach(name -> {
					User user = new User(name, name.toLowerCase() + "@soee.com",33,"");
					userRepository.save(user);
				});
			if(logger.isDebugEnabled())
				userRepository.findAll().forEach(System.out::println);
		};
	}
}
