package models;

import java.util.List;
import java.util.Set;

public interface CarsDao<T> {

    //CRUD Operations
    public T findById(int id);
    public List findAll();
    public T update(T dto);
    public T create(T dto);
    public void delete(int id);

}
