package orderApp.service;

import org.springframework.data.domain.Page;

import orderApp.entity.Restaurant;

public interface RestaurantService {
	
	Restaurant createRestaurant(Restaurant restaurant);
	
	Restaurant getById(Integer id);
	
	Page getAllRestaurants(int pageNum,int pageSize, String sortBy);
	
	Restaurant updateRestaurant(Integer id,Restaurant restaurant);
}
