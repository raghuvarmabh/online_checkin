package com.rv.online_checkin.checkins;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="checkins")
public class Checkin {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @NotBlank(message = "Name is required")
  @Size(max = 50)
  private String name;
  
  @NotBlank(message = "Phone number is required")
  @Size(max = 10, message= "Phone number should be 10 numbers")
  @Size(min = 10, message= "Phone number should be 10 numbers")
  private String phone_number;
  
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime checkedInAt = LocalDateTime.now();

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updatedAt = LocalDateTime.now();
  
  private String state = "open";
  
  public Checkin() {
	  
  }
 
  
  public Checkin(@NotBlank(message = "Name is required") @Size(max = 50) String name,
		@NotBlank(message = "Phone number is required") @Size(max = 10, message = "Phone number should be 10 numbers") @Size(min = 10, message = "Phone number should be 10 numbers") String phone_number) {
	super();
	this.name = name;
	this.phone_number = phone_number;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getPhone_number() {
	return phone_number;
}

public void setPhone_number(String phone_number) {
	this.phone_number = phone_number;
}

public LocalDateTime getCheckedInAt() {
	return checkedInAt;
}

public void setCheckedInAt(LocalDateTime checkedInAt) {
	this.checkedInAt = checkedInAt;
}

public LocalDateTime getUpdatedAt() {
	return updatedAt;
}

public void setUpdatedAt(LocalDateTime updatedAt) {
	this.updatedAt = updatedAt;
}





}
