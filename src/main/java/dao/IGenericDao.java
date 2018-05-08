package dao;

import java.util.List;

public interface IGenericDao<T>{
    T insert(T obj);

    void update(T obj);

    List<T> getAll();

    void removeById(int id);
}
