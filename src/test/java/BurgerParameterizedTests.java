import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParameterizedTests {
    private Burger burger;

    @Mock
    private Ingredient mockIngredient;

    @Mock
    private Bun mockBun;

    private float bunPrice;
    private String bunName;

    private float ingredientPrice;
    private String ingredientName;
    private IngredientType ingredientType;

    private float expectedBurgerPrice;
    private String expectedReceipt;

    public BurgerParameterizedTests(float bunPrice, float ingredientPrice,  float expectedBurgerPrice,
                                    String bunName, String ingredientName, IngredientType ingredientType,  String expectedReceipt){
        this.bunPrice = bunPrice;
        this.bunName = bunName;

        this.ingredientPrice = ingredientPrice;
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;

        this.expectedBurgerPrice = expectedBurgerPrice;
        this.expectedReceipt = expectedReceipt;
    }

    @Before
    public void before() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        when(mockBun.getPrice()).thenReturn(bunPrice);
        when(mockBun.getName()).thenReturn(bunName);

        when(mockIngredient.getPrice()).thenReturn(ingredientPrice);
        when(mockIngredient.getName()).thenReturn(ingredientName);
        when(mockIngredient.getType()).thenReturn(ingredientType);

    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 988.0f, 90.0f, 2066.0f, "Флюоресцентная булка R2-D3", "Соус Spicy-X", IngredientType.SAUCE,
                        "(==== Флюоресцентная булка R2-D3 ====)" + System.lineSeparator() +
                                "= sauce Соус Spicy-X =" + System.lineSeparator() +
                                "(==== Флюоресцентная булка R2-D3 ====)" + System.lineSeparator() + System.lineSeparator() +
                                "Price: 2066,000000" + System.lineSeparator() },
                { 1255.0f, 1337.0f, 3847.0f, "Краторная булка N-200i", "Мясо бессмертных моллюсков Protostomia", IngredientType.FILLING,
                        "(==== Краторная булка N-200i ====)" + System.lineSeparator() +
                                "= filling Мясо бессмертных моллюсков Protostomia =" + System.lineSeparator() +
                                "(==== Краторная булка N-200i ====)" + System.lineSeparator() + System.lineSeparator() +
                                "Price: 3847,000000" + System.lineSeparator() },
                { 3.0f, 1.0f, 7.0f, "WholeGrain", "Bacon", IngredientType.FILLING,
                        "(==== WholeGrain ====)" + System.lineSeparator() +
                                "= filling Bacon =" + System.lineSeparator() +
                                "(==== WholeGrain ====)" + System.lineSeparator() + System.lineSeparator() +
                                "Price: 7,000000" + System.lineSeparator() }
        });
    }

    @Test
    public void addIngredientBurgerTest() {
        Ingredient newIngredient = new Ingredient(ingredientType.FILLING,"Плоды Фалленианского дерева", 874.0f);
        burger.addIngredient(newIngredient);
        Assert.assertTrue(burger.ingredients.contains(newIngredient));
    }

    @Test
    public void removeIngredientBurgerTest() {
        burger.removeIngredient(0);
        Assert.assertFalse(burger.ingredients.contains(mockIngredient));

    }

    @Test
    public void moveIngredientBurgerTest() {
        Ingredient anotherIngredient = new Ingredient(ingredientType.FILLING,"Плоды Фалленианского дерева", 874.0f);
        burger.addIngredient(anotherIngredient);
        burger.moveIngredient(1, 0);
        assertEquals(anotherIngredient, burger.ingredients.get(0));
        assertEquals(mockIngredient, burger.ingredients.get(1));
    }

    @Test
    public void getPriceBurgerTest() {
        assertEquals(expectedBurgerPrice, burger.getPrice(), 0.01f);
    }

    @Test
    public void getReceiptBurgerTest() {
        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
