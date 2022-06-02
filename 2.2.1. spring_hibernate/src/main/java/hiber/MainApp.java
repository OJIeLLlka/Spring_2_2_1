package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW", 5)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("AUDI", 7)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("MERCEDES", 4)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("AUDI", 7)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
      }

      User usersWithCar = userService.listUsersWithCar("AUDI" , 7);
         System.out.println("\nUsers with specific car:\n");
         System.out.println("Id = "+usersWithCar.getId());
         System.out.println("First Name = "+usersWithCar.getFirstName());
         System.out.println("Last Name = "+usersWithCar.getLastName());
         System.out.println("Email = "+usersWithCar.getEmail());
         System.out.println("Car = " + usersWithCar.getCar());

      context.close();
   }
}
