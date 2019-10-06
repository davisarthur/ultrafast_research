public class MagneticField3D {

// instance variables

   int numSides;
   double current;
   double radius;
   double phi;
   double[] xEdgePoints;
   double[] yEdgePoints;
   double length;

// constructor

   public MagneticField3D(int numSidesIn, double radiusIn, double currentIn) {
   
      numSides = numSidesIn;
      radius = radiusIn;
      current = currentIn;
      phi = phi();
      xEdgePoints = xEdgePoints();
      yEdgePoints = yEdgePoints();
      length = length();
   
   }

// methods

   public double phi() {
   
      double phi = Math.PI - ((numSides - 2) * Math.PI / numSides);
   
      return phi;
   }

   private double[] xEdgePoints() {
   
      double[] xEndpoints = new double[numSides];
   
      for (int i = 0; i < numSides; i++) {
         xEndpoints[i] = radius * Math.cos(i * phi);
      }
   
      return xEndpoints;
   }

   private double[] yEdgePoints() {
   
      double[] yEndpoints = new double[numSides];
   
      for (int i = 0; i < numSides; i++) {
         yEndpoints[i] = radius * Math.sin(i * phi);
      }
   
      return yEndpoints;
   }

   public double length() {
   
      double length = 2 * radius * Math.sin((Math.PI
         - (Math.PI * (numSides - 2) / numSides)) / 2);
   
      return length;
   }

   public double fieldFiniteWireY(double a, double b, double c) {
   
      double coefficient = c / ((Math.pow(b, 2) + Math.pow(c, 2)));
   
      double firstTerm = (length - a) / Math.pow(Math.pow(length - a, 2)
         + Math.pow(b, 2) + Math.pow(c, 2), (1 / 2));
   
      double secondTerm = a / Math.pow((Math.pow(a, 2) + Math.pow(b, 2)
         + Math.pow(c, 2)), (1 / 2));
   
      double field = coefficient * (firstTerm + secondTerm);
   
      return field;
   }

   public double fieldFiniteWireZ(double a, double b, double c) {
   
      double coefficient = c / ((Math.pow(b, 2) + Math.pow(c, 2)));
   
      double firstTerm = (length - a) / Math.pow(Math.pow(length - a, 2)
         + Math.pow(b, 2) + Math.pow(c, 2), (1 / 2));
   
      double secondTerm = a / Math.pow((Math.pow(a, 2) + Math.pow(b, 2)
         + Math.pow(c, 2)), (1 / 2));
   
      double field = coefficient * (firstTerm + secondTerm);
   
      return field;
   }

}
