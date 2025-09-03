package orderApp.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentDto {
	@NotNull
	@Schema(description = "This field specifies the list of OrderItemRequest objects")
	private List<OrderItemRequest> orderItems;
	
	@Schema(description = "This field specfies if the payment is successful or not", example = "true")
	private boolean paymentSuccessful;
	
	@NotNull
	@Schema(description = "This field specifies the restaurant ID", example = "52")
	private Integer restaurantId;
	
	@NotNull
	@Schema(description = "This field specifies the user ID", example = "2")
	private Integer userId;
}
