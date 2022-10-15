package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(0, 0), LocalTime.of(23, 0), 2000);
        mealsTo.forEach(System.out::println);
//
//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        HashMap<LocalDate, Integer> mapCalcCalories = new HashMap<>();
        ArrayList<LocalDate> datesWithExcess = new ArrayList<>();
        ArrayList<UserMealWithExcess> listUserMealWithExcess = new ArrayList<>();

        for(UserMeal meal : meals){
            LocalDate dateMeal = meal.getDateTime().toLocalDate();
            if(mapCalcCalories.get(dateMeal) == null){
                mapCalcCalories.put(dateMeal, meal.getCalories());
            }else{
                mapCalcCalories.put(dateMeal, mapCalcCalories.get(dateMeal) + meal.getCalories());
            }

            if(mapCalcCalories.get(dateMeal) > caloriesPerDay && !datesWithExcess.contains(dateMeal)){
                datesWithExcess.add(dateMeal);
            }
        }

        for(UserMeal meal : meals){
            LocalDate dateMeal = meal.getDateTime().toLocalDate();
            LocalTime timeMeal = meal.getDateTime().toLocalTime();

            if(datesWithExcess.contains(dateMeal) && TimeUtil.isBetweenHalfOpen(timeMeal, startTime, endTime)){
                listUserMealWithExcess.add(new UserMealWithExcess(meal.getDateTime(), meal.getDescription(),
                        meal.getCalories(), true));
            }
        }
        return listUserMealWithExcess;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        return null;
    }
}
