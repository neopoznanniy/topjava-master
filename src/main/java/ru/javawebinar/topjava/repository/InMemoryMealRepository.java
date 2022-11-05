package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryMealRepository implements MemoryRepository{
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();

    @Override
    public Map<Integer, Meal> getRepository() {
        return repository;
    }
}