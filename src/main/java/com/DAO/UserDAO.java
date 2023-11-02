package com.DAO;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

@Repository
public interface UserDAO extends CrudRepository<User, String> {
	@Modifying
	@Query("update User u set u.password = :password where u.username = :name")
	void updatePassword(@Param("name")String name,@Param("password") String password);
}
