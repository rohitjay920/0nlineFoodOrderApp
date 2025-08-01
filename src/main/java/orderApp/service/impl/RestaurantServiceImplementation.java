package orderApp.service.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orderApp.entity.Restaurant;
import orderApp.repository.RestaurantRepository;
import orderApp.service.RestaurantService;

@Service	
public class RestaurantServiceImplementation implements RestaurantService{
	
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}

	@Override
	public Restaurant getById(Integer id) {
		Optional<Restaurant> response = restaurantRepository.findById(id);
		
		if(response.isPresent()) {
			return response.get();
		}
		else {
			throw new NoSuchElementException("Restaurant with ID: "+id+" not found");
		}
		
//		return restaurantRepository.findById(id).orElseThrow(()->new NoSuchElementException("Restaurant with ID: "+id+" not found"));
	}

}
