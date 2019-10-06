import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class CircleApproximationTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

   @Test public void phiTest() {
      CircleApproximation test = new CircleApproximation(6, 1);
      Assert.assertEquals(60 * Math.PI / 180, test.phi(), .00001);
   }

}
