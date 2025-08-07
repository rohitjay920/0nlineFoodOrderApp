package orderApp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import orderApp.dto.BillResponse;
import orderApp.dto.OrderItemRequest;
import orderApp.dto.OrderRequest;
import orderApp.dto.PaymentDto;
import orderApp.entity.Food;
import orderApp.entity.Order;
import orderApp.entity.OrderItem;
import orderApp.entity.OrderStatus;
import orderApp.entity.Restaurant;
import orderApp.exception.PaymentFailedException;
import orderApp.repository.OrderRepository;
import orderApp.service.FoodService;
import orderApp.service.OrderService;
import orderApp.service.RestaurantService;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService{
	
	private final RestaurantService restaurantService;
	private final FoodService foodService;
	private final OrderRepository orderRepository;

	@Override
	public BillResponse generateBill(OrderRequest orderRequest) {
		Restaurant restaurant = restaurantService.getById(orderRequest.getRestaurantId());
		StringBuilder summary = new StringBuilder();
		
		float totalPrice=0;
		
		for(OrderItemRequest orderItem : orderRequest.getOrderItems()) {
			Food food = foodService.getFoodById(orderItem.getFoodId());
			float price = food.getPrice() * orderItem.getQuantity();
			totalPrice+=price;
			summary.append(food.getName()).append(" X ").append(orderItem.getQuantity())
				.append(" = ").append(price).append("\n");
		}
		
		return new BillResponse(restaurant.getName(),summary.toString(),totalPrice);
	}

	@Override
	public String payAndPlaceOrder(PaymentDto payment) {
		//simulate payment
		if(payment.isPaymentSuccessful()) {
			Order order = new Order();
			order.setStatus(OrderStatus.PLACED);
			
			Restaurant restaurant = restaurantService.getById(payment.getRestaurantId());
			//set restaurant to order
			order.setRestuarant(restaurant);
			
			List<OrderItem> items = new ArrayList();
			double totalPrice=0;
			
			for(OrderItemRequest request : payment.getOrderItems()) {
				Food food = foodService.getFoodById(request.getFoodId());
				
				OrderItem orderItem = new OrderItem();
				orderItem.setFood(food);
				orderItem.setQuantity(request.getQuantity());
				
				items.add(orderItem);
				
				double price = food.getPrice() * request.getQuantity();
				totalPrice += price;
			}
			
			order.setTotalPrice(totalPrice);
			order.setOrderItems(items);
			orderRepository.save(order);
			return "Order has been placed";
		}
		else {
			throw new PaymentFailedException("Payment was not successful, hence order cannot be placed");
		}
	}
	
	
}
