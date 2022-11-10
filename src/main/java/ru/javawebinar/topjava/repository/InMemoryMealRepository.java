package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealRepository implements MemoryRepository{
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger count = new AtomicInteger(1);

    @Override
    public Map<Integer, Meal> getRepository() {
        return repository;
    }

    @Override
    public Collection<Meal> getAll(){
        return repository.values();
    }

    @Override
    public Meal getById(Integer id) {
        return repository.get(id);
    }

    @Override
    public void delete(Integer id) {
        repository.remove(id);
    }

    @Override
    public Meal save(Meal meal) {
        if(meal.isNew()){
            meal.setId(count.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }



}