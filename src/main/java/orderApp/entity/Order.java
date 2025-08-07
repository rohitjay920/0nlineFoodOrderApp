package orderApp.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private Restaurant restuarant;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	private Double totalPrice;
}
