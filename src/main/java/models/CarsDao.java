package models;

import java.util.Set;

public interface CarsDao {
    Cars getCars();
    Set<Cars> getAllCars();
    Cars getCarsByCarIdAndVin();
    boolean insertCars();
    boolean updateCars();
    boolean deleteCars();
}
