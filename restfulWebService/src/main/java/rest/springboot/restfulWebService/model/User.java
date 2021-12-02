package rest.springboot.restfulWebService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User class provides holder for USER entity attributes.
 * @author adash
 * @version Java: 1.8, Spring boot: 2.3.1
 */
@Entity
@Table
public class User {
	/** Logger instance for User class. */
	private static final Logger LOGGER = LoggerFactory.getLogger(User.class);
	
	/** Primary key for USER entity. */
	@Id
	@GeneratedValue
	private long uid;

	/** Stores name of the user. */
	@Column
	private String name;

	/** Stores address of the user. */
	@Column
	private String address;

	/** Stores phone number of the user. */
	@Column
	private long phone;

	/** Stores email id of the user. */
	@Column(unique=true)
	private String email;

	/** Default constructor for USER entity. */
	public User() {
		super();
	}

	/** Parameterized constructor for USER entity. */
	public User(String name, String address, long phone, String email) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	/*public User(long uid, String name, String address, long phone, String email) {
		super();
		this.uid = uid;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}*/

	/**
	 * Retrieves user id from user's data.
	 * @return uid: User id of user.
	 */
	public long getUid() {
		LOGGER.trace("Retrieving user id from USER repo.");
		
		return uid;
	}

	/**
	 * Retrieves user id from user's data.
	 * @return name: Name of user.
	 */
	public String getName() {
		LOGGER.trace("Retrieving user's name from USER data.");
		
		return name;
	}

	/**
	 * Updates name of user.
	 */
	public void setName(String name) {
		LOGGER.trace("Setting user's name in USER data.");
		
		this.name = name;
	}

	/**
	 * Retrieves address from user's data.
	 * @return address: Address of user.
	 */
	public String getAddress() {
		LOGGER.trace("Retrieving user's address from USER data.");
		
		return address;
	}

	/**
	 * Updates address of user.
	 */
	public void setAddress(String address) {
		LOGGER.trace("Setting user's address in USER data.");
		
		this.address = address;
	}

	/**
	 * Retrieves phone number from user's data.
	 * @return phone: Phone number of user.
	 */
	public long getPhone() {
		LOGGER.trace("Retrieving user's phone number from USER data.");
		
		return phone;
	}

	/**
	 * Updates phone number of user.
	 */
	public void setPhone(long phone) {
		LOGGER.trace("Setting user's phone number in USER data.");
		
		this.phone = phone;
	}

	/**
	 * Retrieves email id from user's data.
	 * @return email: Email id of user.
	 */
	public String getEmail() {
		LOGGER.trace("Retrieving user's email id from USER data.");
		
		return email;
	}

	/**
	 * Updates email id of user.
	 */
	public void setEmail(String email) {
		LOGGER.trace("Setting user's email id in USER data.");
		
		this.email = email;
	}
}