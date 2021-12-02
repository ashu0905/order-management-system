package rest.springboot.restfulWebService.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import rest.springboot.restfulWebService.model.User;
import rest.springboot.restfulWebService.responseHandler.ResponseHandler;

/**
 * UserService class provides methods containing business logic for USER entity.
 * It interacts with DAO of USER entity. 
 * @author adash
 * @version Java: 1.8, Spring boot: 2.3.1
 */
@Component
public interface UserService {

	public User addNewUser(User user) throws SQLIntegrityConstraintViolationException;
	public List<User> getAllUsers();
	public Optional<User> getUser(long id);
	public User updateUserData(User user);
	public void removeUser(long id);
	public void removeUsers();
	public ResponseHandler validateUser(User user);
}