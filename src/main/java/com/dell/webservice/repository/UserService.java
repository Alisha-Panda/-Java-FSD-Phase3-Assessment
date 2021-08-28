package com.dell.webservice.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dell.webservice.entity.User;
import com.dell.webservice.interfaces.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> getEntityUsers(Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<User> pagedResult =  userRepository.findAll(paging);
		if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<User>();
        }
	}
	public Optional<User> getEntityUser(int userId){
	
//		if(getUser != null) {
//		}
		return this.userRepository.findById(userId);
	}
	public void addEntityUser(User addUser) {
//		if(addUser != null) {
//			
//		}
		this.userRepository.save(addUser);
	}
	
	public void updateEntityUser(User updateUser) {
//		if(addUser != null) {
//			
//		}
		this.userRepository.save(updateUser);
	}
	
	public void deleteEntityUser(int userId) {
//		if(addUser != null) {
//			
//		}
		this.userRepository.deleteById(userId);
	}
}
