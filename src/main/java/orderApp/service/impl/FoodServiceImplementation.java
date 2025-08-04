package orderApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orderApp.entity.Food;
import orderApp.repository.FoodRepository;
import orderApp.service.FoodService;

@Service
public class FoodServiceImplementation implements FoodService{
	
	@Autowired
	private FoodRepository foodRepository;
	
	@Override
	public Food createFood(Food food) {	
		return foodRepository.save(food);
	}

}
