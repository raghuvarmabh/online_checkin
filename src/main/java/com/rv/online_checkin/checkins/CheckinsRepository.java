package com.rv.online_checkin.checkins;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CheckinsRepository extends CrudRepository<Checkin, Long>{	
	@Query("select c FROM Checkin c where c.state = 'open'")
	List<Checkin> retrieveActiveCheckins(LocalDate checkedInDate);
}
