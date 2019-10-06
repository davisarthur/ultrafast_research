public class CircleApproxMainTest { 

   public static void main(String[] args) {
   
   // set up
   /**
      MagneticFieldCircLoop test = new MagneticFieldCircLoop(6, 1, 1);
      double a = .9;
      double b = .9;
      
      System.out.print("\nField at point (" + a + ", " + b + "): ");
   
      System.out.println(test.fieldAtPoint(a, b));
      
      System.out.println("\nfields from specific wires: \n");
   
      for (int i = 0; i < test.numSides; i++) {
         System.out.println("wire " + i + ": " + test.wireFields(a, b)[i]);
      }
      */
      
      MagneticFieldCircLoop test = new MagneticFieldCircLoop(8, 1, 1);
      double a = 0;
      double b = .5;
      
      System.out.print("\nField at point (" + a + ", " + b + "): ");
   
      System.out.println(test.fieldAtPoint(a, b));
      
      System.out.println("\nfields from specific wires: \n");
   
      for (int i = 0; i < test.circle.numSides; i++) {
         System.out.println("wire " + i + ": " + test.wireFields(a, b)[i]);
      }
   }
}