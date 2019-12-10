package com.rv.online_checkin.checkins;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckinsResource {

	@Autowired
	CheckinsRepository checkinsRepository;

	@PostMapping("/checkins")
	public Checkin createCheckin(@Valid @RequestBody Checkin checkin){
		   
		Checkin createdCheckin = checkinsRepository.save(checkin);
		System.out.println(createdCheckin.getId());
		   
	  return createdCheckin;
	}
	
	
	@GetMapping("/checkins/{id}")
	public Optional<Checkin> getCheckin(@PathVariable Long id){
		return checkinsRepository.findById(id);
	}
	
	@GetMapping("/checkins/activeCheckins")
	public List<Checkin> getActiveCheckin(){
		return checkinsRepository.retrieveActiveCheckins(LocalDate.now());
	}
}
