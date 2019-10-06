public class CoordinateSystem {

// instance variables

   int numPoints;
   double[] xPoints;
   double[] yPoints;
   double first;
   double last;
   double increment;

// constructor

   public CoordinateSystem(int numPointsIn, double firstIn, double lastIn) {
      numPoints = numPointsIn;
      first = firstIn;
      last = lastIn;
      increment = increment();
      xPoints = new double[numPoints];
      setX();
      yPoints = new double[numPoints];
      setY();
      
   }

// methods

   private double increment() {
   
      double distance = last - first;
      double increment = distance / (numPoints - 1);
   
      return increment;
   }

   private void setX() {
   
      for (int i = 0; i < numPoints; i++) {
         xPoints[i] = first + i * increment;
      }
   }
   
   private void setY() {
   
      for (int i = 0; i < numPoints; i++) {
         yPoints[i] = first + i * increment;
      }
   }

}