package orderApp.dto;

import java.util.List;

import lombok.Data;

@Data
public class PaymentDto {
	private List<OrderItemRequest> orderItems;
	private boolean isPaymentSuccessful;
	private Integer restaurantId;
}
