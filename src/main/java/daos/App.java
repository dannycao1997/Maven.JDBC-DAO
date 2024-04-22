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
            Cars newCar = new Cars(0, "Nissan", "Skyline GTR", 2001, "Bayside Blue", "1234567890VIN");
            Cars createdCar = carsDao.create(newCar);
            if (createdCar != null) {
                System.out.println("Created Car: " + createdCar);
            } else {
                System.out.println("Failed to create car.");
            }

            // Find car by ID
            if (createdCar != null) {
                Cars foundCar = carsDao.findById(createdCar.getId());
                if (foundCar != null) {
                    System.out.println("Found Car: " + foundCar);

                    // Update car
                    foundCar.setColor("White");
                    Cars updatedCar = carsDao.update(foundCar);
                    if (updatedCar != null) {
                        System.out.println("Updated Car: " + updatedCar);
                    } else {
                        System.out.println("Failed to update car.");
                    }

                    // Delete car
                    carsDao.delete(updatedCar.getId());
                    System.out.println("Deleted Car with ID: " + updatedCar.getId());
                } else {
                    System.out.println("No car found with ID: " + createdCar.getId());
                }
            }

            // Find all cars
            List<Cars> cars = carsDao.findAll();
            if (!cars.isEmpty()) {
                System.out.println("All cars:");
                for (Cars car : cars) {
                    System.out.println(car);
                }
            } else {
                System.out.println("No cars found in the database.");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }
}