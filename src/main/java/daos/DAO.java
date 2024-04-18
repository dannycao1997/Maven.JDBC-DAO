package daos;
import java.util.List;

public abstract class DAO<T extends DTO> {

    //CRUD Operations
    public abstract T findById(int id);
    public abstract List<T> findAll();
    public abstract T update(T dto);
    public abstract T create(T dto);
    public abstract void delete(int id);

}
