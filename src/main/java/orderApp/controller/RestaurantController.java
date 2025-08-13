package orderApp.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import orderApp.dto.ResponseStructure;
import orderApp.entity.Food;
import orderApp.entity.Order;
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
		ResponseStructure<Restaurant> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Restaurant object found");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<Page>> getAllRestaurants(
			@RequestParam(defaultValue = "0",required = false) int pageNum,
			@RequestParam(defaultValue = "5", required = false) int pageSize,
			@RequestParam(defaultValue = "createdAt", required = false) String sortBy){
		Page response = restaurantService.getAllRestaurants(pageNum,pageSize,sortBy);
		ResponseStructure<Page> apiResponse = new ResponseStructure();
		apiResponse.setData(response);
		apiResponse.setStatusCode(HttpStatus.OK.value());
		apiResponse.setMessage("Api ran successfully");
		return ResponseEntity.ok(apiResponse);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<Restaurant>> updateRestaurant(@PathVariable Integer id,
			@RequestBody Restaurant updatedRestaurant){
		Restaurant updated = restaurantService.updateRestaurant(id, updatedRestaurant);
		ResponseStructure<Restaurant> apiResponse = new ResponseStructure<>();
		apiResponse.setData(updated);
		apiResponse.setMessage("Restaurant object updated");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity deleteRestaurant(@PathVariable Integer id) {
		restaurantService.deleteRestaurant(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/{restaurantId}/assignFood")
	public ResponseEntity<ResponseStructure<Restaurant>> assignFood(@PathVariable Integer restaurantId, @RequestBody Set<Integer> food){
		Restaurant restaurant = restaurantService.assignFood(restaurantId, food);
		ResponseStructure<Restaurant> apiResponse = new ResponseStructure<>();
		apiResponse.setData(restaurant);
		apiResponse.setMessage("Assigned");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/{id}/getAll")
	public ResponseEntity<ResponseStructure<List<Food>>> getFoodByRestaurant(@PathVariable Integer id){
		ResponseStructure<List<Food>> apiResponse = new ResponseStructure();
		apiResponse.setData(restaurantService.findFoodByRestaurantId(id));
		apiResponse.setMessage("Food items found");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/{id}/getAllOrders")
	public ResponseEntity<ResponseStructure<List<Order>>> getOrdersByRestaurant(@PathVariable Integer id){
		ResponseStructure<List<Order>> apiResponse = new ResponseStructure();
		apiResponse.setData(restaurantService.findOrdersByRestaurantId(id));
		apiResponse.setMessage("Orders found");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
}
