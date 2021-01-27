import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.JavaLevel3.lesson6.ArrayApp;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestArrMass {
    int[] a;
    boolean result;


    public TestArrMass(boolean result,int[] a) {
        this.result = result;
        this.a = a;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {true,new int[]{1,1,1,4}},
                {true,new int[]{1,1,4}},
                {false,new int[]{1,1,4,2}},
                {false,new int[]{1,1,1}},
                {false,new int[]{4,4,4}},

        });
    }


    @Test
    public void test() {

       Assert.assertEquals(result,ArrayApp.isOneFour(a));

    }


}
