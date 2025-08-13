package orderApp.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import orderApp.entity.Food;
import orderApp.entity.Order;
import orderApp.entity.Restaurant;

public interface RestaurantService {
	
	Restaurant createRestaurant(Restaurant restaurant);
	
	Restaurant getById(Integer id);
	
	Page getAllRestaurants(int pageNum,int pageSize, String sortBy);
	
	Restaurant updateRestaurant(Integer id,Restaurant restaurant);
	
	void deleteRestaurant(Integer id);
	
	Restaurant assignFood(Integer restaurantId,Set<Integer> foodId);
	
	List<Food> findFoodByRestaurantId(Integer id);
	
	List<Order> findOrdersByRestaurantId(Integer id);
}
