package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;



@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository){
        this.repository = repository;
    }
    //можно тут получить To, а можно в контроллере

    public Collection<Meal> getAll(int authUserId){
        return repository.getAll(authUserId);
    }

    public Meal getById(int mealId, int authUserId){
        return checkNotFoundWithId(repository.get(mealId, authUserId), mealId);
    }

    public void delete(int mealId, int authUserId){
        checkNotFoundWithId(repository.delete(mealId, authUserId), mealId);
    }

    public Meal create(Meal meal, int authUserId){
        return repository.save(meal, authUserId);
    }

    public Meal update(Meal meal, int authUserId){
        return  checkNotFoundWithId(repository.save(meal, authUserId), meal.getId());
    }
}