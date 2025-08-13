package orderApp.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import orderApp.entity.User;
import orderApp.repository.UserRepository;
import orderApp.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService{

	private final UserRepository userRepository;
	
	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUser(Integer id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		throw new NoSuchElementException("User with ID: "+id+" does not exist");
	}
	

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	
	@Override
	public User updateUser(User user, Integer id) {
		User existing = getUser(id);
		existing.setContactNumber(user.getContactNumber());
		existing.setEmail(user.getEmail());
		existing.setGender(user.getGender());
		existing.setName(user.getName());
		existing.setPassword(user.getPassword());
		return userRepository.save(existing);
	}

	@Override
	public void deleteUser(Integer id) {
		User user = getUser(id);
		userRepository.delete(user);
	}

	@Override
	public String uploadImage(MultipartFile file,Integer id) throws IOException{
		byte[] image = file.getBytes();
		User user = getUser(id);
		user.setImage(image);
		userRepository.save(user);
		return "Image uploaded";
	}

	@Override
	public byte[] getImage(Integer id) {
		User user = getUser(id);
		byte[] image = user.getImage();
		if(image==null || image.length==0) {
			throw new NoSuchElementException("User with ID: "+id+" does not have any image uploaded");
		}
		return image;
	}
	
	
	
	

}
