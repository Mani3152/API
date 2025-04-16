package Programs;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SumofElementsArray {
    @Parameters({"browser"})
    @Test
    public void test(String browser) {
        System.out.println(browser);
    }
}
