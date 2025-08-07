package orderApp.service;

import orderApp.dto.BillResponse;
import orderApp.dto.OrderRequest;
import orderApp.dto.PaymentDto;

public interface OrderService {
	
	BillResponse generateBill(OrderRequest orderRequest);
	
	String payAndPlaceOrder(PaymentDto payment);
}
