package ru.kosarev.pizzacloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.kosarev.pizzacloud.Ingredient.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")

public class DesignPizzaController {
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.SAUCE),
                new Ingredient("COTO", "Corn Tortilla", Type.MUSHROOMS),
                new Ingredient("GRBF", "Ground Beef", Type.DOUGH),
                new Ingredient("CARN", "Carnitas", Type.CHEESE),
                new Ingredient("TMTO", "Diced Tomatoes", Type.SAUSAGE),
                new Ingredient("LETC", "Lettuce", Type.DOUGH),
                new Ingredient("CHED", "Cheddar", Type.SAUSAGE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.DOUGH),
                new Ingredient("SRCR", "Sour Cream", Type.MUSHROOMS)
        );
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }
    @ModelAttribute(name = "pizzaOrder")
    public PizzaOrder order() {
        return new PizzaOrder();
    }
    @ModelAttribute(name = "pizza")
    public Pizza taco() {
        return new Pizza();
    }
    @GetMapping
    public String showDesignForm() {
        return "design";
    }
    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
