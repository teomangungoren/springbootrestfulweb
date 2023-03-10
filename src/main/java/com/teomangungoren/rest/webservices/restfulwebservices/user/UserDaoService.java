package com.teomangungoren.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	
	private static List<User> users=new ArrayList<>();
	
	static {
		users.add(new User(1,"teoman",LocalDate.now().minusYears(30)));
		users.add(new User(2,"beyza",LocalDate.now().minusYears(25)));
		users.add(new User(3,"birsen",LocalDate.now().minusYears(20)));
		
	}
	
	private static int usersCount=3;
	
		public List<User> findAll(){
			return users;
		}
		
		public User save(User user) {
			user.setId(++usersCount);
			users.add(user);
			return user;
		}
		
		public void deleteById(int id) {
			Predicate<? super User> predicate=user->user.getId().equals(id);
			users.removeIf(predicate);
		}
		
		
		public User find0ne(int id) {
			Predicate<? super User> predicate=user->user.getId().equals(id);
			return users.stream().filter(predicate).findFirst().orElse(null);
		}
	}
	

