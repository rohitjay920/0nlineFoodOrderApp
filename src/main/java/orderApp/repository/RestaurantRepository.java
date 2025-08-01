package orderApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orderApp.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{

}
