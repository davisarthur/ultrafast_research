public class Searcher {

// instance variables

public double[] inputArrayX;
public double[] inputArrayY;
public double[][] detector;

// constructor

public Searcher(int inputArraySize) {
	inputArrayX = new double[inputArraySize];
	inputArrayY = new double[inputArraySize];
	detector = new double[inputArraySize][inputArraySize];
}

// methods

public void setArrays(double[] inputArrayXIn, double[] inputArrayYIn) {
	inputArrayX = inputArrayXIn;
	inputArrayY = inputArrayYIn;
}


public int findSpaceX(double pointX) {

double location;

for (int i = 0; i < inputArray.length; i++) {

	if (inputArray[i + 1] >= pointX) {
		if (inputArray[i + 1] >= pointX) {

		location = i;

		}
	}

}

return location;
}

public int findSpaceY(double pointY) {

double location;

for (int i = 0; i < inputArray.length; i++) {

	if (inputArray[i + 1] >= pointY) {
		if (inputArray[i + 1] >= pointY) {

		location = i;

		}
	}

}

return location;
}

public void addHit(double pointX, double pointY) {

detector[findSpaceY(pointY)][findSpaceX(pointX)]++;

}

public static void main() {

int numPixels = 25;
double maxRadius = 10;
double pixelLength = maxRadius * 2 / numPixels;
double[] arrayX = new double[numPixels];
double[] arrayY = new double[numPixels];

for (int i = 0; i < inputArray.lenth; i++) {

arrayX[i] = i * pixelLength - maxRadius;
arrayY[i] = i * pixelLength - maxRadius;

}

double pointX = Math.random() * 20 - 10;
double pointY = Math.random() * 20 - 10;

Searcher test = new Searcher(numPixels);
test.setArrays(arrayX, arrayY);
addHit(pointX, pointY);

System.out.println("Point: (" + pointX + ", " + pointY + ")\n");

for (int i = 0; i < numPixels; i++) {
for (int j = 0; i < numPixels; i++) {
	System.out.print(Searcher.detector[j][i]);
}
System.out.print("\n");
}

}
}
