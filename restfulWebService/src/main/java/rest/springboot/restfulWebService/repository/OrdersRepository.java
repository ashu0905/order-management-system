package rest.springboot.restfulWebService.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.springboot.restfulWebService.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
	public Optional<Orders> findByOrderDate(Date orderDate);
}