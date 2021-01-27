import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.JavaLevel3.lesson6.ArrayApp;

public class TestArr {
   static int[] a,b;
    @BeforeClass
    public static void init() {
       a = new int[]{};
       b = new int[]{1,2,3,1,2,3,12,88};
    }

    @Test(expected = RuntimeException.class)
    public void testArr () {

        Assert.assertArrayEquals(a, ArrayApp.arrayFour(b));
    }

    @Test    public void testArr2 () {

        Assert.assertArrayEquals(a, ArrayApp.arrayFour(new int[]{1,2,3,4}));
    }

    @Test    public void testArr3 () {

        Assert.assertArrayEquals(new int[]{1,3,5}, ArrayApp.arrayFour(new int[]{1,2,3,4,1,5,4,1,3,5}));
    }


    @Test    public void testIsFourOne1 () {
        Assert.assertTrue(ArrayApp.isOneFour(new int[]{1,1,4,1}));
    }

    @Test    public void testIsFourOne2 () {
        Assert.assertFalse(ArrayApp.isOneFour(new int[]{1,1,4,1,5}));
    }

    @Test    public void testIsFourOne3 () {
        Assert.assertFalse(ArrayApp.isOneFour(new int[]{1,1}));
    }

    @Test    public void testIsFourOne4 () {
        Assert.assertTrue(ArrayApp.isOneFour(new int[]{1,1,4,1,1,1,1,4,4,4,4}));
    }


}
