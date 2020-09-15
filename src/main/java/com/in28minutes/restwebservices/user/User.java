package com.in28minutes.restwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Details about User")
@Entity
public class User {
  @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Size(min = 2, message = "Name should be min 2 chars")
  @ApiModelProperty(notes = "Name should be min 2 chars")
  private String name;
  @Past(message = "Birth Date should be in past")
  @ApiModelProperty(notes = "Birth Date should be in past")
  private Date birthDate;

  @OneToMany(mappedBy = "user")
  private List<Post> posts;

  public User(int id, String name, Date birthDate) {
    this.id = id;
    this.name = name;
    this.birthDate = birthDate;
  }
}
