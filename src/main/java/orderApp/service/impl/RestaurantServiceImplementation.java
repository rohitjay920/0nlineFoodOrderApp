package orderApp.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	@Override
	public Page getAllRestaurants(int pageNum, int pageSize, String sortBy) {
		Sort sort = Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
		Page page = restaurantRepository.findAll(pageable);
		
		return page;
	}

	@Override
	public Restaurant updateRestaurant(Integer id, Restaurant updatedRestaurant) {
		Restaurant existing = getById(id);
		existing.setAddress(updatedRestaurant.getAddress());
		existing.setContactNumber(updatedRestaurant.getContactNumber());
		existing.setEmail(updatedRestaurant.getEmail());
		existing.setName(updatedRestaurant.getName());
		
		return restaurantRepository.save(existing);
	}

	

}
