public class MagneticLoopMain {

   public static void main(String[] args) {

      int numSides = 100;
      int numPoints = 101;
      double radius = 1;
      double current = Math.pow(10, 7);
      double[][] bField = new double[numPoints][numPoints];
      MagneticFieldCircLoop loop = new MagneticFieldCircLoop(numSides, radius, current);
      CircleApproxPoint point = new CircleApproxPoint(0, 0, loop.circle);


      CoordinateSystem plane = new CoordinateSystem(numPoints, -radius, radius);

      for (int i = 0; i < plane.numPoints; i++) {

         point.setA(plane.xPoints[i]);

         for (int j = 0; j < plane.numPoints; j++) {

            point.setB(plane.yPoints[j]);
            bField[j][i] = loop.fieldAtPoint(point);
         }
      }

      for (int i = 0; i < plane.numPoints; i++) {

         System.out.println(bField[numPoints / 2][i]);

      }

      System.out.println("");

      for (int i = 0; i < plane.numPoints; i++) {

         System.out.println(plane.xPoints[i]);

      }

   }
}
