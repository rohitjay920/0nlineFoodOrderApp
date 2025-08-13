package orderApp.service;

import orderApp.dto.BillResponse;
import orderApp.dto.OrderRequest;
import orderApp.dto.PaymentDto;
import orderApp.entity.Order;
import orderApp.entity.OrderStatus;

public interface OrderService {
	
	BillResponse generateBill(OrderRequest orderRequest);
	
	String payAndPlaceOrder(PaymentDto payment);
	
	void deleteOrder(Integer id);
	
	Order getOrder(Integer id);
	
	Order updateStatusByAdmin(OrderStatus status,Integer id);
	
	String cancelOrder(Integer id);
}
