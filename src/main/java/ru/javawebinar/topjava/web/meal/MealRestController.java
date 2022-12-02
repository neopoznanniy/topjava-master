package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    //работает из импорта
    @Autowired
    private MealService service;

    // возвращает всю еду для конкретного пользователя.
    public List<MealTo> getAll() {
        log.info("getAllMeal");
        return MealsUtil.getTos(service.getAll(authUserId()), authUserCaloriesPerDay());
    }
    // возвращает еду конретного пользователя отфильтрованную по времени и дате
    public List<MealTo> getFilteredByDateTime(LocalDateTime startTime, LocalDateTime endTime){
        log.info("getFilteredByDateTime");
        return MealsUtil.getFilteredTos(service.getAll(authUserId()), authUserCaloriesPerDay(), startTime, endTime);
    }

    public MealTo getById(int mealId){
        //тут нужно дать объект в котором указанно превышение, но как его найти хз. Поэтому делаем костыль
        //через создание To без превышения по дефолту, чтобы не застревать тут.
        log.info("getMeal {}", mealId);
        Meal meal = service.getById(mealId, authUserId());
        return new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), false);
    }

    public void delete(int mealId){
        log.info("delete {}", mealId);
        service.delete(mealId, authUserId());
    }

    public Meal create(Meal meal){
        return service.create(meal, authUserId());
    }

    public Meal update(Meal meal){
        return service.update(meal, authUserId());
    }
}