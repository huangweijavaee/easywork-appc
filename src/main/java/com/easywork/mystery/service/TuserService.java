package com.easywork.mystery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.easywork.mystery.entity.Tuser;
import com.easywork.mystery.repository.TuserRepository;

@Transactional
@Service
public class TuserService {
	@Autowired
	private TuserRepository tuserRepository;

	public TuserRepository getTuserRepository() {
		return tuserRepository;
	}
	public Tuser findByNameAndPassword(String name, String password) {
		return tuserRepository.findByNameAndPassword(name, password);

	}

}