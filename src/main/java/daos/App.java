package daos;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Connect to Database
// App runner Class
public class App {
    // Test Connection
    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3306/cars";
        String USER = "danny";
        String PASS = "password";

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            DAO carsDao = new CarsDAO(connection);

            // Create a new car
            Cars newCar = new Cars(0, "Tesla", "Model S", 2021, "Black", "1234567890VIN");
            Cars createdCars = carsDao.create(newCar);
            System.out.println("Created Car: " + createdCars);

            // Find car by ID
            Cars foundCar = carsDao.findById(createdCars.getId());
            System.out.println("Found Car: " + foundCar);

            // Update car
            foundCar.setColor("White");
            Cars updatedCar = carsDao.update(foundCar);
            System.out.println("Updated Car: " + updatedCar);

            // Find all cars
            List<Cars> cars = carsDao.findAll();
            System.out.println("All cars:");
            for (Cars car : cars) {
                System.out.println(cars);
            }

            // Delete car
            carsDao.delete(updatedCar.getId());
            System.out.println("Deleted Car with ID: " + updatedCar.getId());

            connection.close();
        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }
}
