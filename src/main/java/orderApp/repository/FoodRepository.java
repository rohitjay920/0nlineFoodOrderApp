package orderApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orderApp.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Integer>{

}
