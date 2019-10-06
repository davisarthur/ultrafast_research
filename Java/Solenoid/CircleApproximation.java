public class CircleApproximation {

// instance variables

   int numSides = 0;
   double radius = 0;

// constructor

   public CircleApproximation(int numSidesIn, double radiusIn) {
      
      numSides = numSidesIn;
      radius = radiusIn;
   }

// methods

   public double phi() {
   
      double phi = Math.PI - ((numSides - 2) * Math.PI / numSides);
      
      return phi;
   }

   public double[] xEndpoints() {
   
      double[] xEndpoints = new double[numSides];
      
      for (int i = 0; i < numSides; i++) {
         xEndpoints[i] = radius * Math.cos(i * phi());
      }
      
      return xEndpoints;
   }
   
   public double[] yEndpoints() {
   
      double[] yEndpoints = new double[numSides];
      
      for (int i = 0; i < numSides; i++) {
         yEndpoints[i] = radius * Math.sin(i * phi());
      }
      
      return yEndpoints;
   }

   public double length() {
   
      double length = 2 * radius * Math.sin((Math.PI
         - (Math.PI * (numSides - 2) / numSides)) / 2);
         
      return length;
   }

   public double[] parSlopes() {
   
      double[] parSlopes = new double[numSides];
      
      for (int i = 0; i < numSides - 1; i++) {
      
         boolean xDiffPoss = (xEndpoints()[i + 1] - xEndpoints()[i]) > 0;
         boolean yDiffPoss = (yEndpoints()[i + 1] - yEndpoints()[i]) > 0;
         
         if ((xDiffPoss && yDiffPoss) || (!(xDiffPoss) && !(yDiffPoss))) {
            parSlopes[i] = Math.abs((yEndpoints()[i + 1] - yEndpoints()[i])
               / (xEndpoints()[i + 1] - xEndpoints()[i]));
         }
         
         else {
            parSlopes[i] = - Math.abs((yEndpoints()[i + 1] - yEndpoints()[i])
               / (xEndpoints()[i + 1] - xEndpoints()[i]));
         }
      }
      
      boolean xDiffPoss = (xEndpoints()[0] - xEndpoints()[numSides - 1]) > 0;
      boolean yDiffPoss = (yEndpoints()[0] - yEndpoints()[numSides - 1]) > 0;
      
      if ((xDiffPoss && yDiffPoss) || (!(xDiffPoss) && !(yDiffPoss))) {
         parSlopes[numSides - 1] = Math.abs((yEndpoints()[0] - yEndpoints()[numSides - 1])
            / (xEndpoints()[0] - xEndpoints()[numSides - 1]));
      }
      
      else {
         parSlopes[numSides - 1] = - Math.abs((yEndpoints()[0] - yEndpoints()[numSides - 1])
            / (xEndpoints()[0] - xEndpoints()[numSides - 1]));
      }
         
      return parSlopes;
   }
   
   public double[] perpSlopes() {
   
      double[] perpSlopes = new double[numSides];
   
      for (int i = 0; i < numSides; i++) {
         perpSlopes[i] = - 1 / parSlopes()[i];
      }
   
      return perpSlopes;
   }
   
   // a & b are x & y positions
   public double[] xStars(double a, double b) {
   
      double[] xStars = new double[numSides];
   
      for (int i = 0; i < numSides; i++) {
         xStars[i] = (perpSlopes()[i] * a + yEndpoints()[i] - b
               - parSlopes()[i] * xEndpoints()[i]) / (perpSlopes()[i]
               - parSlopes()[i]);
      }
      
      return xStars;
   }
   
   public double[] yStars(double a, double b) {
   
      double[] yStars = new double[numSides];
   
      for (int i = 0; i < numSides; i++) {
         yStars[i] = (yEndpoints()[i] + parSlopes()[i] 
            * (a - xEndpoints()[i] - b / perpSlopes()[i]))
            / (1 - parSlopes()[i] / perpSlopes()[i]);
      }
         
      return yStars;
   }
   
   public double[] parLengths1(double a, double b) {
   
      double[] parLengths1 = new double[numSides];
   
      for (int i = 0; i < numSides; i++) {
         parLengths1[i] = Math.sqrt(Math.pow(xEndpoints()[i] 
            - xStars(a, b)[i], 2) + Math.pow(yEndpoints()[i] 
            - yStars(a, b)[i], 2));
      }
      
      return parLengths1;
   }
   
   public double[] parLengths2(double a, double b) {
   
      double[] parLengths2 = new double[numSides];
   
      for (int i = 0; i < numSides - 1; i++) {
         parLengths2[i] = Math.sqrt(Math.pow(xEndpoints()[i + 1]
            - xStars(a, b)[i], 2) + Math.pow(yEndpoints()[i + 1]
            - yStars(a, b)[i], 2));
      }
      
      parLengths2[numSides - 1] = Math.sqrt(Math.pow(xEndpoints()[0]
         - xStars(a, b)[numSides - 1], 2) + Math.pow(yEndpoints()[0]
         - yStars(a, b)[numSides - 1], 2));
      
      return parLengths2;
   }
   
   public double[] perpLengths(double a, double b) {
   
      double[] perpLengths = new double[numSides];
   
      for (int i = 0; i < numSides; i++) {
         perpLengths[i] = Math.sqrt(Math.pow(a - xStars(a, b)[i], 2)
            + Math.pow(b - yStars(a, b)[i], 2));
      }
   
      return perpLengths;
   }
   
   public double[] alphas(double a, double b) {
   
      double[] alphas = new double[numSides];
   
      for (int i = 0; i < numSides; i++) {
         alphas[i] = Math.atan(perpLengths(a, b)[i] 
            / parLengths1(a, b)[i]);
      }
      
      return alphas;
   }
   
   public double[] betas(double a, double b) {
   
      double[] betas = new double[numSides];
   
      for (int i = 0; i < numSides; i++) {
         betas[i] = Math.atan(perpLengths(a, b)[i] 
            / parLengths2(a, b)[i]);
      }
      
      return betas;
   }
   
   public double[] thetas1(double a, double b) {
   
      double[] thetas1 = new double[numSides];
   
      for (int i = 0; i < numSides; i++) {
         thetas1[i] = Math.PI / 2 - alphas(a, b)[i];
      }
   
      return thetas1;
   }
   
   public double[] thetas2(double a, double b) {
   
      double[] thetas2 = new double[numSides];
   
      for (int i = 0; i < numSides; i++) {
         thetas2[i] = Math.PI / 2 - betas(a, b)[i];
      }
   
      return thetas2;
   }
   
   // point falls in the simple region of the wire segment from example
   public boolean[] simpleRegion(double a, double b) {
   
      boolean[] result = new boolean[numSides];
   
      double[] edges1 = new double[numSides];
   
      for (int i = 0; i < numSides; i++) {
         edges1[i] = perpSlopes()[i] * (a - xEndpoints()[i]) + yEndpoints()[i];
      }
      
      double[] edges2 = new double[numSides];
      
      for (int i = 0; i < numSides - 1; i++) {
         edges2[i] = perpSlopes()[i] * (a - xEndpoints()[i + 1]) 
            + yEndpoints()[i + 1];
      }
      
      edges2[numSides - 1] = perpSlopes()[numSides - 1] * (a - xEndpoints()[0]) 
            + yEndpoints()[0];
      
      ArraySorter edgeSorter = new ArraySorter(numSides);
      edgeSorter.sort(edges1, edges2);
      
      for (int j = 0; j < numSides; j++) {
         if (edgeSorter.greatestArray[j] > b && edgeSorter.leastArray[j] < b) {
            result[j] = true;
         }
      }
      
      return result;
   }
}