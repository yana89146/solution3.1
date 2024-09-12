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

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

//    Создала пользователей с машинами
        User user5 = new User("User5", "Lastname5", "user5@mail.ru");
        User user6 = new User("User6", "Lastname6", "user6@mail.ru");
        User user7 = new User("User7", "Lastname7", "user7@mail.ru");

        user5.setCar(new Car("model1", 1));
        user6.setCar(new Car("model2", 2));
        user7.setCar(new Car("model3", 3));

//    Добавила пользователей с машинами
        userService.add(user5);
        userService.add(user6);
        userService.add(user7);


//    Создала метод для извлечения пользователей с машинами
        List<User> users2 = userService.listUserWithCar();
        for (User user : users2) {
            System.out.println("User with car");
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Model = " + user.getCar().getModel());
            System.out.println("Series = " + user.getCar().getSeries());
            System.out.println();
        }

//    Метод, который извлекает 1 пользователя,
//    владеющего конкретной моделью и серией автомобиля
//    (при условии что не будет пользователя с одинаковой серией и моделью)
        User userWithCar = userService.getUserWithCar("model1", 1);
        System.out.println();
        System.out.println("User of car (model1, series1)  = " + userWithCar.getFirstName());

        context.close();
    }
}
