package daos;
import java.util.List;

public interface DAO {

    //CRUD Operations
    Cars findById(int id);
    List<Cars> findAll();
    Cars update(Cars cars);
    Cars create(Cars cars);
    void delete(int id);

}
