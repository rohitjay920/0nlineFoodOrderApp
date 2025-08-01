package orderApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orderApp.dto.ResponseStructure;
import orderApp.entity.Restaurant;
import orderApp.service.RestaurantService;

@RestController
@RequestMapping("/restaurant/api")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Restaurant>> createRestaurant(@RequestBody Restaurant restaurant){
		Restaurant response = restaurantService.createRestaurant(restaurant);
		ResponseStructure apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Restaurant object created successfully!");
		apiResponse.setStatusCode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseStructure<Restaurant>> getById(@PathVariable Integer id){
		Restaurant response = restaurantService.getById(id);
		ResponseStructure apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Restaurant object found");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
}
