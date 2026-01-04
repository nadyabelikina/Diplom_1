import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

@RunWith(MockitoJUnitRunner.class)
public class BurgerMockTests {

    //Burger burger = new Burger();

    private final String nameBun = "Флюоресцентная булка R2-D3";
    private final float priceBun = 988.0f;

    private final String nameIngredientFilling = "Филе Люминесцентного тетраодонтимформа";
    private final float priceIngredientFilling = 988.0f;
    private final IngredientType typeFilling = IngredientType.FILLING;

    private final String nameIngredientSauce = "Соус фирменный Space Sauce";
    private final float priceIngredientSauce = 80.0f;
    private final IngredientType typeSauce = IngredientType.SAUCE;


    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientFillingMock;

    @Mock
    private Ingredient ingredientSauceMock;

    @InjectMocks
    private Burger burger;

    @Before
    public void before() {
        when(bunMock.getName()).thenReturn(nameBun);
        when(bunMock.getPrice()).thenReturn(priceBun);

        when(ingredientFillingMock.getName()).thenReturn(nameIngredientFilling);
        when(ingredientFillingMock.getPrice()).thenReturn(priceIngredientFilling);
        when(ingredientFillingMock.getType()).thenReturn(typeFilling);

        when(ingredientSauceMock.getName()).thenReturn(nameIngredientSauce);
        when(ingredientSauceMock.getPrice()).thenReturn(priceIngredientSauce);
        when(ingredientSauceMock.getType()).thenReturn(typeSauce);
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bunMock);
        Assert.assertEquals(bunMock, burger.bun);

    }

    @Test
    public void addIngredientBurgerTest() {
        burger.addIngredient(ingredientFillingMock);
        Assert.assertTrue(burger.ingredients.contains(ingredientFillingMock));

    }
    @Test
    public void removeIngredientBurgerTest() {
        burger.addIngredient(ingredientFillingMock);
        burger.addIngredient(ingredientSauceMock);
        burger.removeIngredient(0);
        Assert.assertFalse(burger.ingredients.contains(ingredientFillingMock));
    }
    @Test
    public void moveIngredientBurgerTest() {
        burger.addIngredient(ingredientFillingMock);
        burger.addIngredient(ingredientSauceMock);
        burger.moveIngredient(0, 1);
        Assert.assertEquals("Филе Люминесцентного тетраодонтимформа", burger.ingredients.get(1).getName());
    }

    @Test
    public void getPriceBurgerTest() {
        float bunPrice = 988;
        float ingredientPrice = 988;
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientFillingMock);
        Assert.assertEquals(bunPrice * 2 + ingredientPrice, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptBurgerTest() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientFillingMock);
        burger.addIngredient(ingredientSauceMock);

        float mockPrice = bunMock.getPrice() * 2 + ingredientFillingMock.getPrice() + ingredientSauceMock.getPrice();

        String expectReceipt = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n%n" +
                        "Price: %f%n",
                bunMock.getName(),
                ingredientFillingMock.getType().toString().toLowerCase(), ingredientFillingMock.getName(),
                ingredientSauceMock.getType().toString().toLowerCase(), ingredientSauceMock.getName(),
                bunMock.getName(),
                mockPrice);
        Assert.assertEquals(expectReceipt, burger.getReceipt());
    }
}
