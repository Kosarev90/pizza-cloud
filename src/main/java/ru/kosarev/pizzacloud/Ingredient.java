package ru.kosarev.pizzacloud;

import lombok.Data;

@Data
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public enum Type{
        SAUSAGE, CHEESE, DOUGH, SAUCE, MUSHROOMS
    }
}
