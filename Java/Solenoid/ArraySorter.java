public class ArraySorter {

// instance variables
   int elements = 0;
   double[] greatestArray;
   double[] leastArray;

// constructor

   public ArraySorter(int elementsIn) {
      elements = elementsIn;
      greatestArray = new double[elements];
      leastArray = new double[elements];
   }

//methods

   public void sort(double[] array1, double[] array2) {
   
      for (int i = 0; i < elements; i++) {
         if (array1[i] > array2[i]) {
            greatestArray[i] = array1[i];
            leastArray[i] = array2[i];
         }
         else {
            greatestArray[i] = array2[i];
            leastArray[i] = array1[i];
         }
      }
   }
}