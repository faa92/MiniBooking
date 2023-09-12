package com.example.minibooking.repository;

import java.util.Optional;

public interface BaseRepository<E, ID> {

    E getReferenceById(ID id);

    Optional<E> findById(ID id);

    void create(E entity);

    void remove(E entity);

}
