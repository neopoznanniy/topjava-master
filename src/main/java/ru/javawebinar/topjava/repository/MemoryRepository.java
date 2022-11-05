package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Map;

public interface MemoryRepository {

    public Map<Integer, Meal> getRepository();
}
