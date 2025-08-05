package orderApp.controller;

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
import orderApp.service.FoodService;

@RestController
@RequestMapping("/food/api")
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Food>> createFood(@RequestBody Food food){
		Food saved = foodService.createFood(food);
		ResponseStructure<Food> apiResponse = new ResponseStructure<>();
		apiResponse.setData(saved);
		apiResponse.setMessage("Food added successfully!");
		apiResponse.setStatusCode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}/get")
	public ResponseEntity<ResponseStructure<Food>> getFoodById(@PathVariable Integer id){
		Food food = foodService.getFoodById(id);
		ResponseStructure<Food> apiResponse = new ResponseStructure();
		apiResponse.setData(food);
		apiResponse.setMessage("Data found");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<Page<Food>>> getAll(@RequestParam(defaultValue = "0",required = false) Integer pageNum,
			@RequestParam(defaultValue = "5",required = false) Integer pageSize){
		Page<Food> page = foodService.getAllFood(pageNum, pageSize);
		ResponseStructure<Page<Food>> apiResponse = new ResponseStructure();
		apiResponse.setData(page);
		apiResponse.setMessage("Data found");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@PutMapping("/{id}/update")
	public ResponseEntity<ResponseStructure<Food>> updateFood(@RequestBody Food food, @PathVariable Integer id){
		Food updated = foodService.updateFood(food, id);
		ResponseStructure<Food> apiResponse = new ResponseStructure();
		apiResponse.setData(updated);
		apiResponse.setMessage("Food updated");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity deleteFood(@PathVariable Integer id) {
		foodService.deleteFood(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
