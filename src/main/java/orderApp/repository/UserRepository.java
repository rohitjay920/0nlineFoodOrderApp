package orderApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orderApp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
