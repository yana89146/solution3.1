package hiber.service;

import hiber.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    User getUserWithCar(String model, int series);

    List<User> listUserWithCar();

}
