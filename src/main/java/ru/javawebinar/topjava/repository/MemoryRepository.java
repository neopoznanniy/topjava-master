package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.util.Map;

public interface MemoryRepository {

    public Map<Integer, Meal> getRepository();

    public Collection<Meal> getAll();

    public Meal save(Meal meal);

    public Meal getById(Integer id);

    public void delete(Integer id);
}
