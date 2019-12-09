package com.rv.online_checkin.checkins;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CheckinsRepository extends CrudRepository<Checkin, Long>{

}
