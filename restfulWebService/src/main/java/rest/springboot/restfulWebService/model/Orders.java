package rest.springboot.restfulWebService.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Orders {
	@Id
	@GeneratedValue
	private long oid;
	
	@Column
	private long userId;
	
	@Column
	@ElementCollection(targetClass=Long.class)
	private List<Long> productId;
	
	@Column
	private Date orderDate;

	public Orders() {
		super();
	}

	public Orders(Date orderDate, List<Long> productId, long userId) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.orderDate = orderDate;
	}

	public long getOid() {
		return oid;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<Long> getProductId() {
		return productId;
	}

	public void setProductId(List<Long> productId) {
		this.productId = productId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
}