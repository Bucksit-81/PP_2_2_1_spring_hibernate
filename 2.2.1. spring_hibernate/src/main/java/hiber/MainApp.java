package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        Car car1 = new Car("BMW", 5);
        Car car2 = new Car("VAZ", 21065);
        Car car3 = new Car("Hyundai", 35);
        Car car4 = new Car("VW", 6);
        carService.add(car1);
        carService.add(car2);
        carService.add(car3);
        carService.add(car4);

        userService.add(new User("Bucks", "Lastname1", "user1@mail.ru", car4));
        userService.add(new User("Aurora", "Lastname2", "user2@mail.ru", car3));
        userService.add(new User("Vlad", "Lastname3", "user3@mail.ru", car2));
        userService.add(new User("Mike", "Lastname4", "user4@mail.ru", car1));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car  = " + user.getCar1());
        }
        context.close();
    }
}