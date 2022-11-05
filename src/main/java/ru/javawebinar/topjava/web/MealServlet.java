package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.MemoryRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private MemoryRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException{
        this.repository = new InMemoryMealRepository();
        this.repository.getRepository().put(1, new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 300));

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("getAll");

//        request.setAttribute("mealList", MealsUtil.getWithExceeded(MealsUtil.getMeals(), 2000));
        request.setAttribute("mealList", MealsUtil.getWithExceeded(MealsUtil.convertToList(repository.getRepository()), 2000));

        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
