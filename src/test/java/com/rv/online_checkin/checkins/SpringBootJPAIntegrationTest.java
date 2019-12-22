package com.rv.online_checkin.checkins;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class SpringBootJPAIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private CheckinsRepository checkinsRepository;
    
	
	@Test
	public void searchTodaysCheckins() {
	    // given
		Checkin alex = new Checkin("alex", "1234567890");
	    entityManager.persist(alex);
	    entityManager.flush();
	 
	    // when
	    List<Checkin> found = checkinsRepository.retrieveActiveCheckins(LocalDate.now());
	 
	    // then
	    assertFalse(found.isEmpty());
	    assertEquals(found.get(0).getName(), alex.getName());
	}
}
