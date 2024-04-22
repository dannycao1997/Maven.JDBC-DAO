package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarsDAO implements DAO {

    private Connection connection;

    public CarsDAO(Connection connection) {
        this.connection = connection;
    }

    public Cars findById(int id) {
        String query = "SELECT * FROM cars WHERE id = ?";
        System.out.println("Executing query: " + query + " with ID: " + id);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractCarFromResultSet(resultSet);
            } else {
                System.out.println("No car found with ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cars> findAll() {
        List<Cars> cars = new ArrayList<>();
        String query = "SELECT * FROM cars";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                cars.add(extractCarFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public Cars update(Cars cars) {
        String query = "UPDATE cars SET make=?, model=?, year=?, color=?, vin=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cars.getMake());
            statement.setString(2, cars.getModel());
            statement.setInt(3, cars.getYear());
            statement.setString(4, cars.getColor());
            statement.setString(5, cars.getVin());
            statement.setInt(6, cars.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating cars failed");
            } else {
                return cars;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cars create(Cars cars) {
        String query = "INSERT INTO cars (make, model, year, color, vin) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, cars.getMake());
            statement.setString(2, cars.getModel());
            statement.setInt(3, cars.getYear());
            statement.setString(4, cars.getColor());
            statement.setString(5, cars.getVin());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating car failed");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cars.setId(generatedKeys.getInt(1));
                    return cars;
                } else {
                    throw new SQLException("Creating car failed");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM cars WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting car failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Cars extractCarFromResultSet(ResultSet rs) throws SQLException {
        Cars cars = new Cars();
        cars.setId(rs.getInt("id"));
        cars.setMake(rs.getString("make"));
        cars.setModel(rs.getString("model"));
        cars.setYear(rs.getInt("year"));
        cars.setColor(rs.getString("color"));
        cars.setVin(rs.getString("vin"));
        return cars;
    }
}

