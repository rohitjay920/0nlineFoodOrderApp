package orderApp.dto;

import java.util.List;

import lombok.Data;

@Data
public class PaymentDto {
	private List<OrderItemRequest> orderItems;
	private boolean paymentSuccessful;
	private Integer restaurantId;
	private Integer userId;
}
