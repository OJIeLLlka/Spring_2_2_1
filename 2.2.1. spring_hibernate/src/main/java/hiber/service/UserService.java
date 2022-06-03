package hiber.service;

import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    User getUserByModelAndSeries(String model, int series);
}
