package orderApp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemRequest {
	@NotNull
	private Integer foodId;
	@Min(1)
	private int quantity;
}
