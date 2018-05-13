package dao;

import java.util.List;
import java.util.Optional;

public interface IGenericDao<T>{
    T insert(T obj);

    void update(T obj);

    List<T> getAll();

    Optional<T> getById(int id);

    void removeById(int id);
}
