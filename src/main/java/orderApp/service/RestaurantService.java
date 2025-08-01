package orderApp.service;

import orderApp.entity.Restaurant;

public interface RestaurantService {
	
	Restaurant createRestaurant(Restaurant restaurant);
	
	Restaurant getById(Integer id);
}
