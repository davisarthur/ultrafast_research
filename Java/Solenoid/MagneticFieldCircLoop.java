public class MagneticFieldCircLoop {

// instance variables

   double current;
   CircleApproxEdited circle;
   public final static double PERMEABILITY = 4 * Math.PI * Math.pow(10, -7);

// constructor

   MagneticFieldCircLoop(int numSidesIn, double radiusIn, double currentIn) {
      circle = new CircleApproxEdited(numSidesIn, radiusIn);
      current = currentIn;
   }
   
// methods

   public double[] wireFields(CircleApproxPoint point) {
   
      double[] wireFields = new double[circle.numSides];
   
      for (int i = 0; i < circle.numSides; i++) {
         wireFields[i] = current * PERMEABILITY / (4 * Math.PI 
            * point.perpLengths[i]);
      
         if (point.simpleRegion[i]) {
            wireFields[i] *= (Math.sin(point.thetas1[i]) + Math.sin(point.thetas2[i]));
         }
         
         else {
            wireFields[i] *= Math.abs(Math.sin(point.thetas1[i]) 
               - Math.sin(point.thetas2[i]));
         }
      }
      
      return wireFields;
   }

   public double fieldAtPoint(CircleApproxPoint point) {
   
      double[] wireFields = new double[circle.numSides];
   
      for (int i = 0; i < circle.numSides; i++) {
         wireFields[i] = current * PERMEABILITY / (4 * Math.PI * point.perpLengths[i]);
      
         if (point.simpleRegion[i]) {
            wireFields[i] *= (Math.sin(point.thetas1[i]) + Math.sin(point.thetas2[i]));
         }
         
         else {
            wireFields[i] *= Math.abs(Math.sin(point.thetas1[i]) 
               - Math.sin(point.thetas2[i]));
         }
      }
      
      double magField = 0;
      
      for (double field : wireFields) {
         magField += field;
      }
      
      if (Math.sqrt(Math.pow(point.a, 2) + Math.pow(point.b, 2)) > circle.radius) {
         magField *= -1;
      }
      if (Math.abs(Math.sqrt(Math.pow(point.a, 2) + Math.pow(point.b, 2)) 
            - circle.radius) < .0001) {
         magField = 0;
      }
      
      return magField;
   }
}