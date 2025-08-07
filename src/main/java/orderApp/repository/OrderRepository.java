package orderApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orderApp.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
