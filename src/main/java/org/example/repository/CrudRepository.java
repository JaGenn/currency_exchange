package org.example.repository;

import java.util.List;

public interface CrudRepository<T, ID> {

    List<T> selectAll();

    T save(T entity);

    void delete(ID id);
}
