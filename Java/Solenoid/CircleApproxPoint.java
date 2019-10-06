public class CircleApproxPoint {

// instance variables

   double a = 0;
   double b = 0;
   CircleApproxEdited circle;
   double[] xStars;
   double[] yStars;
   double[] parLengths1;
   double[] parLengths2;
   double[] perpLengths;
   double[] thetas1;
   double[] thetas2;
   boolean[] simpleRegion;

// constructor

   public CircleApproxPoint(double aIn, double bIn, CircleApproxEdited circleIn) {
      a = aIn;
      b = bIn;
      circle = circleIn;
      xStars = new double[circle.numSides];
      xStars = xStars();
      yStars = new double[circle.numSides];
      yStars = yStars();
      parLengths1 = new double[circle.numSides];
      parLengths1 = parLengths1();
      parLengths2 = new double[circle.numSides];
      parLengths2 = parLengths2();
      perpLengths = new double[circle.numSides];
      perpLengths = perpLengths();
      thetas1 = new double[circle.numSides];
      thetas1 = thetas1();
      thetas2 = new double[circle.numSides];
      thetas2 = thetas2();
      simpleRegion = new boolean[circle.numSides];
      simpleRegion = simpleRegion();
   }

// methods

   private double[] xStars() {
   
      double[] xStars = new double[circle.numSides];
   
      for (int i = 0; i < circle.numSides; i++) {
         xStars[i] = (circle.perpSlopes[i] * a + circle.yEndpoints[i] - b
               - circle.parSlopes[i] * circle.xEndpoints[i]) / (circle.perpSlopes[i]
               - circle.parSlopes[i]);
      }
      
      return xStars;
   }
   
   private double[] yStars() {
   
      double[] yStars = new double[circle.numSides];
   
      for (int i = 0; i < circle.numSides; i++) {
         yStars[i] = (circle.yEndpoints[i] + circle.parSlopes[i] * (a 
            - circle.xEndpoints[i] - b / circle.perpSlopes[i])) / (1 
            - circle.parSlopes[i] / circle.perpSlopes[i]);
      }
         
      return yStars;
   }
   
   private double[] parLengths1() {
   
      double[] parLengths1 = new double[circle.numSides];
   
      for (int i = 0; i < circle.numSides; i++) {
         parLengths1[i] = Math.sqrt(Math.pow(circle.xEndpoints[i] - xStars[i], 2) 
            + Math.pow(circle.yEndpoints[i] - yStars[i], 2));
      }
      
      return parLengths1;
   }
   
   private double[] parLengths2() {
   
      double[] parLengths2 = new double[circle.numSides];
   
      for (int i = 0; i < circle.numSides - 1; i++) {
         parLengths2[i] = Math.sqrt(Math.pow(circle.xEndpoints[i + 1] - xStars[i], 2) 
            + Math.pow(circle.yEndpoints[i + 1] - yStars[i], 2));
      }
      
      parLengths2[circle.numSides - 1] = Math.sqrt(Math.pow(circle.xEndpoints[0] 
         - xStars[circle.numSides - 1], 2) + Math.pow(circle.yEndpoints[0] 
         - yStars[circle.numSides - 1], 2));
      
      return parLengths2;
   }
   
   private double[] perpLengths() {
   
      double[] perpLengths = new double[circle.numSides];
   
      for (int i = 0; i < circle.numSides; i++) {
         perpLengths[i] = Math.sqrt(Math.pow(a - xStars[i], 2)
            + Math.pow(b - yStars[i], 2));
      }
   
      return perpLengths;
   }
   
   private double[] thetas1() {
   
      double[] thetas1 = new double[circle.numSides];
   
      for (int i = 0; i < circle.numSides; i++) {
         thetas1[i] = Math.PI / 2 - Math.atan(perpLengths[i] 
            / parLengths1[i]);
      }
      
      return thetas1;
   }
   
   private double[] thetas2() {
   
      double[] thetas2 = new double[circle.numSides];
   
      for (int i = 0; i < circle.numSides; i++) {
         thetas2[i] = Math.PI / 2 - Math.atan(perpLengths[i] 
            / parLengths2[i]);
      }
      
      return thetas2;
   }
   
   // point falls in the simple region of the wire segment from example
   private boolean[] simpleRegion() {
   
      boolean[] result = new boolean[circle.numSides];
   
      double[] edges1 = new double[circle.numSides];
   
      for (int i = 0; i < circle.numSides; i++) {
         edges1[i] = circle.perpSlopes[i] * (a - circle.xEndpoints[i]) 
            + circle.yEndpoints[i];
      }
      
      double[] edges2 = new double[circle.numSides];
      
      for (int i = 0; i < circle.numSides - 1; i++) {
         edges2[i] = circle.perpSlopes[i] * (a - circle.xEndpoints[i + 1]) 
            + circle.yEndpoints[i + 1];
      }
      
      edges2[circle.numSides - 1] = circle.perpSlopes[circle.numSides - 1] 
         * (a - circle.xEndpoints[0]) + circle.yEndpoints[0];
      
      ArraySorter edgeSorter = new ArraySorter(circle.numSides);
      edgeSorter.sort(edges1, edges2);
      
      for (int j = 0; j < circle.numSides; j++) {
         if (edgeSorter.greatestArray[j] > b && edgeSorter.leastArray[j] < b) {
            result[j] = true;
         }
      }
      
      return result;
   }

   public void setA(double newA) {
      a = newA;
      xStars = xStars();
      yStars = yStars();
      parLengths1 = parLengths1();
      parLengths2 = parLengths2();
      perpLengths = perpLengths();
      thetas1 = thetas1();
      thetas2 = thetas2();
      simpleRegion = simpleRegion();
   }

   public void setB(double newB) {
      b = newB;
      xStars = xStars();
      yStars = yStars();
      parLengths1 = parLengths1();
      parLengths2 = parLengths2();
      perpLengths = perpLengths();
      thetas1 = thetas1();
      thetas2 = thetas2();
      simpleRegion = simpleRegion();
   }
}