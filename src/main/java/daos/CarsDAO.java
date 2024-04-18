package daos;

import java.util.Collections;
import java.util.List;

public class CarsDAO extends DAO<Cars>{
    @Override
    public Cars findById(int id) {
        return null;
    }

    @Override
    public List<Cars> findAll() {
        return Collections.emptyList();
    }

    @Override
    public Cars update(Cars dto) {
        return null;
    }

    @Override
    public Cars create(Cars dto) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
