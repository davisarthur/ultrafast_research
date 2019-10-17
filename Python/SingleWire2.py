# Davis Arthur
# 10-14-2019
# Auburn University

import math
# science constants
import scipy.constants

# plotting libraries
import matplotlib.pyplot as plt
import numpy as np

# Calculates the magnetic field of a single wire at a given point, current, and
# length of the wire
class stwire:

    # pos: position of the edge of the wire in world space
    def __init__(self, current, length, pos1):
        self.current = current
        self.length = length
        self.r = pos1[0]
        self.theta = pos1[1]  # needs to be in radians
        self.z = pos1[2]

    def __str__(self):
        return "Wire details: \n Current: " + str(self.current) \
            + "\n Length: " + str(self.length) + "\n Position: " \
            + "(" + str(self.r) + ", " + str(self.theta) + ", " + str(self.z) \
            + ")"

    # calculates the x and z components of the magnetic field relative to
    # local space
    def maglocal(self, x0, y0, z0):

        # coefficient outside the integral in Biot Savart Law
        co = scipy.constants.mu_0 * self.current / (4 * scipy.constants.pi)

        lsq = x0 ** 2 + z0 ** 2

        # if the magnetic field blows up a certain point, call the magnetic field zero
        if (lsq == 0) or (x0 == 0 or z0 == 0) and (y0 > 0 and y0 < self.length):
            field = (0, 0)
        # otherwise calculate the magnetic field
        else:
            # part of the magnetic field that depends soley on y position
            genMag = y0 / (lsq * (y0 ** 2 + lsq) ** (1 / 2.0)) \
                - (y0 - self.length) / (lsq * ((y0 - self.length) ** 2 + lsq) \
                ** (1 / 2.0))
            # magnetic field in the x direction
            xMag = co * z0 * genMag
            # magnetic field in the y direction
            zMag = - co *  x0 * genMag

            field = (xMag, zMag)

        return field

    # takes in a point in a global space and translates the point to the local
    # space of the wire
    def gtol(self, gpos):
        x = gpos[0]
        y = gpos[1]
        z = gpos[2]

        x1 = self.r * math.cos(self.theta)
        y1 = self.r * math.sin(self.theta)
        z1 = self.z

        x0 = (x - x1) * math.cos(self.theta) + (y - y1) * math.sin(self.theta)
        y0 =  - (x - x1) * math.sin(self.theta) + (y - y1) * math.cos(self.theta)
        z0 = z - z1

        lpos = (x0, y0, z0)

        return lpos


# Takes 3d array of points and returns 2d plane at desired index
# axisNum: 0 (yz plane), 1 (xz plane), 2 (xy plane)
def planeSelector(array, sizeX, sizeY, axisNum, index):
    output = []

    if axisNum == 0:
        output = array[index]

    elif axisNum == 1:
        for i in range(sizeX):
            output.append(array[i][index])

    elif axisNum == 2:
        for i in range(sizeX):
            intermediate = []
            for j in range(sizeY):
                intermediate.append(array[i][j][index])
            output.append(intermediate)

    return output

# takes in 2d array and outputs
def lineSelector(array, index):

    output = array[index]

    return output


# main method
# plots the z component of the magnetic field in the x y plane
def main1():
    numPoints = input("Number of points in calculation: ")
    index = input("z-index: ")
    length = input("length: ")
    # generate single wire
    testwire = stwire(1.0, length, (0, 0, 0))
    # generate array of 3D points
    x = []
    y = []
    z = []

    for i in range(numPoints):
        x.append(i - (numPoints / 2.0))
        y.append(i - (numPoints / 2.0))
        z.append(i - (numPoints / 2.0))

    zmag = [[[0 for k in range(numPoints)] for j in range(numPoints)] \
        for i in range(numPoints)]

    for xindex in range(numPoints):
        for yindex in range(numPoints):
            for zindex in range(numPoints):
                xcord = x[xindex]
                ycord = y[yindex]
                zcord = z[zindex]
                zmag[yindex][xindex][zindex] = \
                    testwire.maglocal(xcord, ycord, zcord)[1]

    print("Plotting the field at z = " + str(z[index]))

    finalfield = planeSelector(zmag, numPoints, numPoints, 2, index)
    plt.contourf(x, y, finalfield, 100, cmap = 'BrBG')
    plt.colorbar()
    plt.show()

def main2():
    # input values from the user
    numPoints = input("Number of points in calculation: ")
    index = input("z-index: ")
    length = input("length: ")
    radius = input("radius: ")
    angle = input("angle: ")

    # generate single wire
    testwire = stwire(1.0, length, (radius, math.radians(angle), 0))

    # generate array of 3D points
    x = []
    y = []
    z = []

    for i in range(numPoints):
        x.append(i - (numPoints / 2.0))
        y.append(i - (numPoints / 2.0))
        z.append(i - (numPoints / 2.0))

    zmag = [[[0 for k in range(numPoints)] for j in range(numPoints)] \
        for i in range(numPoints)]


    for xindex in range(numPoints):
        for yindex in range(numPoints):
            for zindex in range(numPoints):
                xcord = x[xindex]
                ycord = y[yindex]
                zcord = z[zindex]
                xlocal, ylocal, zlocal = testwire.gtol([xcord, ycord, zcord])
                zmag[yindex][xindex][zindex] = zmag[yindex][xindex][zindex] \
                    + testwire.maglocal(xlocal, ylocal, zlocal)[1]

    print("Plotting the field at z = " + str(z[index]))

    finalfield = planeSelector(zmag, numPoints, numPoints, 2, index)
    plt.contourf(x, y, finalfield, 100, cmap = 'BrBG')
    plt.colorbar()
    plt.show()


if __name__ == "__main__":
     main2()
