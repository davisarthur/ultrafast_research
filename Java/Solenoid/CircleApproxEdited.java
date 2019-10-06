public class CircleApproxEdited {

// instance variables

   int numSides = 0;
   double radius = 0;
   double phi;
   double[] xEndpoints;
   double[] yEndpoints;
   double[] parSlopes;
   double[] perpSlopes;

// constructor

   public CircleApproxEdited(int numSidesIn, double radiusIn) {
      
      numSides = numSidesIn;
      radius = radiusIn;
      phi = phi();
      xEndpoints = new double[numSides];
      xEndpoints = xEndpoints();
      yEndpoints = new double[numSides];
      yEndpoints = yEndpoints();
      parSlopes = new double[numSides];
      parSlopes = parSlopes();
      perpSlopes = new double[numSides];
      perpSlopes = perpSlopes();
   }

// methods

   private double phi() {
   
      double phi = Math.PI - ((numSides - 2) * Math.PI / numSides);
      
      return phi;
   }

   private double[] xEndpoints() {
   
      double[] xEndpoints = new double[numSides];
      
      for (int i = 0; i < numSides; i++) {
         xEndpoints[i] = radius * Math.cos(i * phi);
      }
      
      return xEndpoints;
   }
   
   private double[] yEndpoints() {
   
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

   private double[] parSlopes() {
   
      double[] parSlopes = new double[numSides];
      
      for (int i = 0; i < numSides - 1; i++) {
      
         boolean xDiffPoss = (xEndpoints[i + 1] - xEndpoints[i]) > 0;
         boolean yDiffPoss = (yEndpoints[i + 1] - yEndpoints[i]) > 0;
         
         if ((xDiffPoss && yDiffPoss) || (!(xDiffPoss) && !(yDiffPoss))) {
            parSlopes[i] = Math.abs((yEndpoints[i + 1] - yEndpoints[i])
               / (xEndpoints[i + 1] - xEndpoints[i]));
         }
         
         else {
            parSlopes[i] = - Math.abs((yEndpoints[i + 1] - yEndpoints[i])
               / (xEndpoints[i + 1] - xEndpoints[i]));
         }
      }
      
      boolean xDiffPoss = (xEndpoints[0] - xEndpoints[numSides - 1]) > 0;
      boolean yDiffPoss = (yEndpoints[0] - yEndpoints[numSides - 1]) > 0;
      
      if ((xDiffPoss && yDiffPoss) || (!(xDiffPoss) && !(yDiffPoss))) {
         parSlopes[numSides - 1] = Math.abs((yEndpoints[0] - yEndpoints[numSides - 1])
            / (xEndpoints[0] - xEndpoints[numSides - 1]));
      }
      
      else {
         parSlopes[numSides - 1] = - Math.abs((yEndpoints[0] - yEndpoints[numSides - 1])
            / (xEndpoints[0] - xEndpoints[numSides - 1]));
      }
         
      return parSlopes;
   }
   
   private double[] perpSlopes() {
   
      double[] perpSlopes = new double[numSides];
   
      for (int i = 0; i < numSides; i++) {
         perpSlopes[i] = - 1 / parSlopes()[i];
      }
   
      return perpSlopes;
   }
}