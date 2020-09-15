package com.in28minutes.restwebservices.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

  @Id
  @GeneratedValue
  private Integer id;

  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private User user;
}
