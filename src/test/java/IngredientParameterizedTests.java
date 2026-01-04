import praktikum.Ingredient;
import praktikum.IngredientType;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IngredientParameterizedTests {
    private String name;
    private float price;
    private IngredientType type;

    public IngredientParameterizedTests(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.FILLING, "Говяжий метеорит (отбивная)", 3000},
                {IngredientType.SAUCE, "Соус фирменный Space Sauce", 80}
        });
    }

    @Test
    public void getTypeIngredientTest() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(type, ingredient.getType());
    }

    @Test
    public void getNameIngredientTest(){
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(name, ingredient.getName());
    }

    @Test
    public void getPriceIngredientTest(){
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(price, ingredient.getPrice(), 0);
    }
}
