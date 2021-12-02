package rest.springboot.restfulWebService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.springboot.restfulWebService.model.User;

/**
 * UserRepository interface provides methods for UserService class to 
 * implement.
 * @author adash
 * @version Java: 1.8, Spring boot: 2.3.1
 */
public interface UserRepository extends JpaRepository<User, Long> {}