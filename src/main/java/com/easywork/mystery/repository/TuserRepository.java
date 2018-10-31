package com.easywork.mystery.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.easywork.mystery.entity.Tuser;
import com.easywork.mystery.repository.TuserRepository;

public interface TuserRepository extends JpaRepository<Tuser, Serializable> {
	
	public Tuser findByNameAndPassword(String name,String password);
}