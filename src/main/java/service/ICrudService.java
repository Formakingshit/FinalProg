package service;


import java.util.List;

public interface ICrudService<T> {

    T create(T t);

    List<T> getById(int id);

    void update(T t);

    void deleteById(int id);
}