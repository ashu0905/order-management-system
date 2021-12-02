package rest.springboot.restfulWebService.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.springboot.restfulWebService.model.User;
import rest.springboot.restfulWebService.responseHandler.ResponseHandler;
import rest.springboot.restfulWebService.service.UserService;

/**
 * OMSController class provides request handling methods for User, Product & Order APIs. 
 * @author adash
 * @version Java: 1.8, Spring boot: 2.3.1
 */
@RestController
@RequestMapping("/oms")
public class UserController {

	/** Logger instance for OMSController class. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	/** Injecting object dependency of UserService. */
	@Autowired
	UserService userService;

	/**
	 * Creates a new user in User entity in H2 db.
	 * Calls addNewUser() from UserService class to create new user 
	 * in USER.
	 * @param user: User JSON containing name, address, phone & email of
	 * user to be created.
	 * @return User: New user created.
	 */
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		LOGGER.trace("Creating a new User.");

		ResponseHandler validateResp = userService.validateUser(user);

		if(HttpStatus.OK == validateResp.getResponseCode()) {
			LOGGER.info("Calling UserService.addNewUser method to create a new user.");
			User _user = null;
			try {
				_user = userService.addNewUser(user);
			} catch (SQLIntegrityConstraintViolationException sicve) {
				LOGGER.error(sicve.getMessage());
				System.out.println("returning from catch");
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			if(null == _user) {
				LOGGER.error("Failed to create new user in repo.");
				System.out.println("returning from internal if");

				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<>(_user, HttpStatus.CREATED);
			}
		} else {
			LOGGER.error(validateResp.getResponseStatus());

			return new ResponseEntity<>(null, validateResp.getResponseCode());
		}
	}

	/**
	 * Retrieves list of all users from User entity in H2 db.
	 * Calls getAllUsers() from UserService class to get the list of all 
	 * users from USER.
	 * @return List<User>: List of all users.
	 */
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		LOGGER.trace("Retrieving list of all users.");

		try {
			List<User> users = new ArrayList<User>();
			LOGGER.info("Calling UserService.getAllUsers method to list all users.");
			users = userService.getAllUsers();

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Retrieves user from User entity in H2 db based on user's id.
	 * Calls getUser() from UserService class to retrieve user from 
	 * USER.
	 * @param id: Id of user to be retrieved.
	 * @return User: User's data retrieved.
	 */
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		LOGGER.trace("Retrieving user by User Id.");

		LOGGER.info("Calling userService.getUser method to retrieve user's data by id.");
		Optional<User> userData = userService.getUser(id);

		try {
			if (userData.isPresent()) {
				return new ResponseEntity<>(userData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch(NullPointerException npe) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Updates user in User entity in H2 db.
	 * Calls getUser() from UserService class to retrieve user from 
	 * USER based on parameter "id".
	 * Calls updateUserData() from UserService class to update user's 
	 * data in USER.
	 * @param id: Id of user to be updated.
	 * @param user: User's data to be updated to.
	 * @return User: User's data updated.
	 */
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {

		Optional<User> usersData = userService.getUser(id);
		if(usersData.isPresent()) {
			User _user = usersData.get();
		}
		LOGGER.trace("Updating user");

		LOGGER.info("Calling userService.getUser method to retrieve user's data by id.");
		Optional<User> userData = userService.getUser(id);

		if (userData.isPresent()) {
			User _user = userData.get();
			_user.setName(user.getName());
			_user.setAddress(user.getAddress());
			_user.setPhone(user.getPhone());
			_user.setEmail(user.getEmail());

			LOGGER.info("Calling userService.updateUserData method to update user's data.");
			return new ResponseEntity<>(userService.updateUserData(_user), HttpStatus.OK);
		} else {
			LOGGER.error("Failed to update user in repo: No such user found in repo.");

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Deletes user from User entity in H2 db.
	 * Calls removeUser() from UserService class to delete user from USER.
	 * @param id: Id of user to be deleted.
	 * @return Http status code for deletion; 204- Success, 500- Failure. 
	 */
	@DeleteMapping("/user/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
		LOGGER.trace("Deleting user.");

		try {
			LOGGER.info("Calling userService.removeUser method to delete user.");
			userService.removeUser(id);
			LOGGER.info("User successfully deleted from repo.");

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			LOGGER.error("Failed to delete user from repo.");

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Deletes all users from User entity in H2 db.
	 * Calls removeUsers() from UserService class to delete all users from USER.
	 * @return Http status code for deletion; 204- Success, 500- Failure.
	 */
	@DeleteMapping("/users")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		LOGGER.trace("Deleting all users.");

		try {
			LOGGER.info("Calling userService.removeUsers method to delete all users.");
			userService.removeUsers();
			LOGGER.info("All users successfully deleted from repo.");

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			LOGGER.error("Failed to deleted all users from repo.");

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}