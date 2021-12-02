package rest.springboot.restfulWebService.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

import rest.springboot.restfulWebService.model.Orders;

/**
 * OrderService class provides methods containing business logic for ORDERS entity.
 * It interacts with DAO of ORDERS entity. 
 * @author adash
 * @version Java: 1.8, Spring boot: 2.3.1
 */
@Component
public interface OrderService {
	public Orders addNewOrder(Orders order);
	public List<Orders> getAllOrders();
	public Optional<Orders> getOrderById(long id);
	public List<Orders> getOrderByDate(Date orderDate);
	public Orders updateOrderData(Orders order);
	public void removeOrder(long id);
	public void removeOrders();
}