package com.iudigital.services;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iudigital.models.Register;
import com.iudigital.repositories.RegisterRepository;

@Service
public class RegisterServices {
	
	@Autowired
	RegisterRepository registerRepo;
	
	public void createRegister(Register reg) {
		reg.setCheckIn(LocalDateTime.now());
		registerRepo.save(reg);
	}
	
	public void putRegister(int id) {
		Register reg = registerRepo.findById(id).get();
		reg.setCheckOut(LocalDateTime.now());
		long a=reg.getCheckIn().getHour();
		long b= reg.getCheckOut().getHour();
		reg.setPrice((b-a)*5000);
		reg.setState(false);
		registerRepo.save(reg);
		
	}
	
	public ArrayList<Register> getAllRegister(){
		return  (ArrayList<Register>) registerRepo.findAll();
	}
	
	
	public ArrayList<Register> getActiveRegister(){
		
		ArrayList<Register> r =(ArrayList<Register>) registerRepo.findAll();
		
		ArrayList<Register> m = new ArrayList<>();
		for(Register i:r) {
			if(i.isState()) {
				m.add(i);
			}
		}
		
		Collections.sort(m,(a,b) -> Integer.valueOf(a.getCell()).compareTo(Integer.valueOf(b.getCell())));

		
		return m;
		
	}
	
	public Register getRegister(int id) {
		return registerRepo.findById(id).get();
	}

}
