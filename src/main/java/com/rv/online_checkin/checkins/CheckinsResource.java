package com.rv.online_checkin.checkins;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rv.online_checkin.AuthenticationRequest;
import com.rv.online_checkin.AuthenticationResponse;
import com.rv.online_checkin.JwtUtil;
import com.rv.online_checkin.UserDetailsService;
import com.rv.online_checkin.models.User;
import com.rv.online_checkin.models.UserRepository;

@RestController
@RequestMapping("/api")
public class CheckinsResource {

	@Autowired
	CheckinsRepository checkinsRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;
	

	@PostMapping("/checkins")
	public Checkin createCheckin(@Valid @RequestBody Checkin checkin){
		   
		Checkin createdCheckin = checkinsRepository.save(checkin);
		System.out.println(createdCheckin.getId());
		   
	  return createdCheckin;
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@GetMapping("/checkins/{id}")
	public Optional<Checkin> getCheckin(@PathVariable int id){
		return checkinsRepository.findById(id);
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUser(@PathVariable int id){
		return userRepository.findById(id);
	}
	
	@GetMapping("/checkins/activeCheckins")
	public List<Checkin> getActiveCheckin(){
		return checkinsRepository.retrieveActiveCheckins(LocalDate.now());
	}
	
	@GetMapping("/")
	public String home(){
		return ("<h1>Welcome!</h1>");
	}
	
	@GetMapping("/user")
	public String user(){
		return ("<h1>Welcome user!</h1>");
	}
	
	@GetMapping("/admin")
	public String admin(){
		return ("<h1>Welcome admin!</h1>");
	}
}
