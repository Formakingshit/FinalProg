package service;

import java.util.Optional;

public interface ICrudService<T> {

    T create(T t);

    Optional<T> getById(int id);

    void update(T t);

    void deleteById(int id);
}