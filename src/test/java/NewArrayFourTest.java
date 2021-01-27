import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.JavaLevel3.lesson6.ArrayApp;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class NewArrayFourTest {

        int[] a;
        int[] b;


        public NewArrayFourTest(int[] a, int[] b) {
            this.a = a;
            this.b = b;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {new int[]{5, 2, 3},new int[]{1,2,3,4,5,2,3}},
                    {new int[]{},new int[]{1,2,3,4,5,2,3,4}},
                    {new int[]{1, 2, 3},new int[]{1,2,3,4,1,2,3}},
                    {new int[]{5},new int[]{1,2,3,4,5}},


            });
        }


        @Test
        public void testSum() {
            Assert.assertArrayEquals(a, ArrayApp.arrayFour(b));

        }



}
