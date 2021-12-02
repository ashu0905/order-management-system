package rest.springboot.restfulWebService.serviceImpl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rest.springboot.restfulWebService.model.User;
import rest.springboot.restfulWebService.repository.UserRepository;
import rest.springboot.restfulWebService.responseHandler.ResponseHandler;
import rest.springboot.restfulWebService.service.UserService;

/**
 * UserService class provides methods containing business logic for USER entity.
 * It interacts with DAO of USER entity. 
 * @author adash
 * @version Java: 1.8, Spring boot: 2.3.1
 */
@Service
public class UserServiceImpl implements UserService {

	/** Logger instance for UserService class. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	/** Injecting object dependency of UserRepository. */
	@Autowired
	UserRepository userRepository;

	/**
	 * Creates a new user in User entity in H2 db.
	 * Implements save() from UserRepository interface to create new user 
	 * in USER.
	 * @param user: User JSON containing name, address, phone & email of
	 * user to be created.
	 * @return User: new user created.
	 */
	@Transactional
	public User addNewUser(User user) throws SQLIntegrityConstraintViolationException {
		LOGGER.trace("Creating a new user in USER repo.");

		User _user = userRepository
				.save(new User(user.getName(), user.getAddress(), user.getPhone(), user.getEmail()));
		
		if(null == _user) {
			return null;
		}
		
		LOGGER.debug("New user created as: {}", _user);
		
		return _user;
	}

	/**
	 * Retrieves list of all users from User entity in H2 db.
	 * Implements findAll() from UserRepository interface to get the list of all 
	 * users from USER.
	 * @return List<User>: List of all users.
	 */
	@Transactional
	public List<User> getAllUsers() {	
		LOGGER.trace("Retrieving list of users from USER repo.");

		List<User> users = new ArrayList<User>();
		userRepository.findAll().forEach(users::add);

		if (users.isEmpty()) {
			LOGGER.error("No User data exists in USER repo.");
			return null;
		}

		LOGGER.debug("Users retrieved from USER repo: {}", users);
		return users;
	}

	/**
	 * Retrieves user from User entity in H2 db based on user's id.
	 * Implements findById() from UserRepository interface to retrieve user from 
	 * USER.
	 * @param id: Id of user to be retrieved.
	 * @return User: User's data retrieved.
	 */
	@Transactional
	public Optional<User> getUser(long id) {
		LOGGER.trace("Retrieving user by id from USER repo.");

		Optional<User> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			LOGGER.debug("User retrieved from USER repo: {}", userData);
			return userData;
		} else {
			LOGGER.error("No such user id exists in USER repo.");
			return null;
		}
	}

	/**
	 * Updates user in User entity in H2 db.
	 * Implements save() from UserRepository interface to update user's 
	 * data in USER.
	 * @param user: User's data to be updated to.
	 * @return User: User's data updated.
	 */
	@Transactional
	public User updateUserData(User user) {
		LOGGER.trace("Updating user data in USER repo.");

		User _user = userRepository.save(user);
		LOGGER.debug("User updated to: {}", _user);

		return _user;
	}

	/**
	 * Deletes user from User entity in H2 db.
	 * Implements deleteById() from UserRepository interface to delete user from USER.
	 * @param id: Id of user to be deleted. 
	 */
	@Transactional
	public void removeUser(long id) {
		LOGGER.trace("Deleting user data from USER repo.");

		userRepository.deleteById(id);
	}
	
	/**
	 * Deletes all users from User entity in H2 db.
	 * Implements deleteAll() from UserRepository interface to delete all users from USER.
	 */
	@Transactional
	public void removeUsers() {
		LOGGER.trace("Deleteing all users from USER repo.");

		userRepository.deleteAll();
	}

	public ResponseHandler validateUser(User user) {
		
		if(user.getName() == null) {
			LOGGER.error("Name of user is null");
			return new ResponseHandler(HttpStatus.BAD_REQUEST, "User name can't be null");
		}
		if(!(user.getName().matches("^[a-zA-Z]*$"))) {
			LOGGER.error("Name of user must be a string.");
			return new ResponseHandler(HttpStatus.BAD_REQUEST, "User name must be a string.");
		}
		
		if(user.getAddress() == null) {
			LOGGER.error("Address of user is null");
			return new ResponseHandler(HttpStatus.BAD_REQUEST, "User address can't be null");
		}
		
		if(user.getPhone() == 0) {
			LOGGER.error("Phone number of user is null");
			return new ResponseHandler(HttpStatus.BAD_REQUEST, "User phone number can't be null");
		}
		
		if(user.getEmail() == null) {
			LOGGER.error("Email id of user is null");
			return new ResponseHandler(HttpStatus.BAD_REQUEST, "User email id can't be null");
		}
		
		return new ResponseHandler(HttpStatus.OK, "User request body is valid");
	}
}