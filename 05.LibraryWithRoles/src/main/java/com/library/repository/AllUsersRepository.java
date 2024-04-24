package com.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.AllUsers;

//library v2
@Repository
public interface AllUsersRepository extends JpaRepository<AllUsers,Integer> 
{
	Optional<AllUsers> findByEmail(String email);
}
