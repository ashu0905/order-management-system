package rest.springboot.restfulWebService;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * RestfulWebServiceApplication is the main class to enable 
 * auto-configuration of the components.
 * @author adash
 * @version Java: 1.8, Spring boot: 2.3.1
 */
@SpringBootApplication
@EntityScan(basePackages= {"rest.springboot.restfulWebService.model"})
public class RestfulWebServiceApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(RestfulWebServiceApplication.class, args);
	}
}