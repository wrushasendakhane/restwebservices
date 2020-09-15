package com.in28minutes.restwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

  private static List<User> users = new ArrayList<>();
  private int userCount = 3;

  static {
    users.add(new User(1, "Adam", new Date()));
    users.add(new User(2, "Eve", new Date()));
    users.add(new User(3, "Jack", new Date()));
  }

  public List<User> findAll() {
    return users;
  }

  public User findById(int id) {
    for (User user : users) {
      if (user.getId() == id) {
        return user;
      }
    }
    return null;
  }

  public User deleteById(int id) {
    Iterator<User> iterator = users.iterator();
    while (iterator.hasNext()) {
      User user = iterator.next();
      if (user.getId() == id) {
        iterator.remove();
        return user;
      }
    }
    return null;
  }

  public User save(User user) {
    if (user.getId() == 0) {
      user.setId(++userCount);
    }
    users.add(user);
    return user;
  }
}
