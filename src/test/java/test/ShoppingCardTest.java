package test;

import com.mcan.ty.model.*;
import com.mcan.ty.util.DiscountType;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class ShoppingCardTest {

    private ShoppingCard shoppingCard;
    private Category food;
    private Category fruid;

    private Product apple;
    private Product almond;

    private Campaign campaign1;
    private Campaign campaign2;
    private Campaign campaign3;
    private Campaign campaign4;

    private Coupon coupon;

    @Before
    public void before() {
        shoppingCard = new ShoppingCard();

        food = new Category("Food");
        fruid = new Category("Fruid");

        fruid.setParent(food);

        apple = new Product("Apple", 100.0, fruid);
        almond = new Product("Almond", 150.0, food);



        campaign1 = new Campaign(food, 10.0, 1, DiscountType.Rate);
        campaign2 = new Campaign(food, 10.0, 1, DiscountType.Rate);
        campaign3 = new Campaign(food, 10.0, 1, DiscountType.Rate);
        campaign4 = new Campaign(food, 10.0, 1, DiscountType.Rate);

        coupon = new Coupon(100, 10, DiscountType.Amount);

    }

    @After
    public void after() {
        shoppingCard = null;
        food = null;
        fruid = null;

        apple = null;
        almond = null;

        campaign1 = null;
        campaign2 = null;
        campaign3 = null;
        campaign4 = null;

        coupon = null;
    }


    @Test
    public void shoppingCardTest1() {
        shoppingCard.addItem(apple, 3);
        shoppingCard.addItem(almond, 1);

        shoppingCard.applyDiscounts(campaign1, campaign2, campaign3, campaign4);
        shoppingCard.applyCoupon(coupon);

        assertEquals(shoppingCard.getTotalAmountAfterDiscounts(), 260.0, 40.0);
    }
}
