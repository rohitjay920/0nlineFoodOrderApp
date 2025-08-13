package orderApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import orderApp.entity.Food;
import orderApp.entity.Order;
import orderApp.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
	
	@Query("SELECT r.food FROM Restaurant r WHERE r.id = :restaurantId")
	List<Food> findFoodByRestaurantId(@Param(value = "restaurantId") int id);
	
	@Query("SELECT r.orders FROM Restaurant r WHERE r.id = :restaurantId")
	List<Order> findOrdersByRestaurantId(@Param(value="restaurantId") int id);
}
