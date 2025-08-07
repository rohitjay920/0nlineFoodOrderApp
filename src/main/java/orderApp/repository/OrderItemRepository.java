package orderApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orderApp.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

}
