package test;

import com.mcan.ty.model.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CategoryTest {
    private Category food;
    private Category fruid;
    private Category meat;
    private Category tropicalFruid;


    @Before
    public void before() {
        food = new Category("Food");
        fruid = new Category("Fruid");
        fruid.setParent(food);

        meat = new Category ("Meat");
        meat.setParent(food);

        tropicalFruid = new Category("Tropical Fruid");
        tropicalFruid.setParent(fruid);

    }

    @After
    public void after(){
        food = null;
        fruid = null;
        meat = null;
        tropicalFruid = null;
    }

    @Test
    public void categoryTest1() {
        assertTrue(food.isEqualOrParent(fruid));
    }


    @Test
    public void categoryTest2() {
        assertFalse(meat.isEqualOrParent(fruid));
    }

    @Test
    public void categoryTest3() {
        assertFalse(tropicalFruid.isEqualOrParent(food));
    }

    @Test
    public void categoryTest4() {
        assertTrue(meat.isEqualOrParent(meat));
    }

}
