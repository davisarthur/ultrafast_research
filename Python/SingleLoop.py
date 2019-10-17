# Davis Arthur
# 10-15-2019
# Auburn University

import math
import scipy.constants # science constants
import matplotlib.pyplot as plt # plotting libraries
import numpy as np

from SingleWire2 import *

class loop:

    def __init__(self, current, radius, numwires, zpos = 0):
        self.current = current
        self.radius = radius
        self.numwires = numwires
        self.wirelen = 0
        self.origins = self.calcOrigins()
        self.zpos = zpos
        self.wires = self.genwires()

    def calcOrigins(self):
        deltaAng = math.radians(360.0 / self.numwires)
        self.wirelen = self.radius * (2 - 2 * math.cos(deltaAng)) ** (1.0/2)
        origins = []

        for i in range(self.numwires):
                origins.append(i * deltaAng)

        return origins

    def genwires(self):

        wires = []

        for origin in self.origins:
            wires.append(stwire(self.current, self.wirelen, (self.radius, origin, self.zpos)))

        return wires


def main3():
    # input values from the user
    current = input("Current of wire: ")
    radius = input("Radius of wire: ")
    height = input("Height of region of interest: ")
    numwires = input("Number of straight wires in approximation: ")
    numPointsX = input("Number of points in x/y direction: ")
    numPointsZ = input("Number of points in z direction: ")
    index = input("z-index: ")

    # generate single wire
    testloop = loop(current, radius, numwires)

    # generate array of 3D points
    x = []
    y = []
    z = []

    deltaX = 2.0 * radius * 1.25 / numPointsX
    deltaZ = height / numPointsZ

    for i in range(numPointsX):
        x.append(- radius * 1.25 + i * deltaX)
        y.append(- radius * 1.25 + i * deltaX)
    for i in range(numPointsZ):
        z.append(i * deltaZ)


    zmag = [[[0 for k in range(numPointsZ)] for j in range(numPointsX)] \
        for i in range(numPointsX)]

    for wire in testloop.wires:
        for xindex in range(numPointsX):
            for yindex in range(numPointsX):
                for zindex in range(numPointsZ):
                    xcord = x[xindex]
                    ycord = y[yindex]
                    zcord = z[zindex]
                    xlocal, ylocal, zlocal = wire.gtol([xcord, ycord, zcord])
                    newcomp = wire.maglocal(xlocal, ylocal, zlocal)[1]
                    zmag[yindex][xindex][zindex] = zmag[yindex][xindex][zindex] \
                        + newcomp

    print("Plotting the field at z = " + str(z[index]))

    finalfield = planeSelector(zmag, numPointsX, numPointsX, 2, index)
    plt.contourf(x, y, finalfield, 300, cmap = 'BrBG')
    plt.colorbar()
    plt.show()


def main4():

    # input values from the user
    current = input("Current of wire: ")
    radius = input("Radius of wire: ")
    height = input("Height of region of interest: ")
    numwires = input("Number of straight wires in approximation: ")
    numPointsX = input("Number of points in x/y direction: ")
    numPointsZ = input("Number of points in z direction: ")
    index = input("z-index: ")

    # generate single wire
    testloop = loop(current, radius, numwires)

    # generate array of 3D points
    x = []
    y = []
    z = []

    deltaX = 2.0 * radius * 1.25 / numPointsX
    deltaZ = height / numPointsZ

    for i in range(numPointsX):
        x.append(- radius * 1.25 + i * deltaX)
        y.append(- radius * 1.25 + i * deltaX)
    for i in range(numPointsZ):
        z.append(i * deltaZ)


    zmag = [[[0 for k in range(numPointsZ)] for j in range(numPointsX)] \
        for i in range(numPointsX)]

    for wire in testloop.wires:
        for xindex in range(numPointsX):
            for yindex in range(numPointsX):
                for zindex in range(numPointsZ):
                    xcord = x[xindex]
                    ycord = y[yindex]
                    zcord = z[zindex]
                    xlocal, ylocal, zlocal = wire.gtol([xcord, ycord, zcord])
                    newcomp = wire.maglocal(xlocal, ylocal, zlocal)[1]
                    zmag[yindex][xindex][zindex] = zmag[yindex][xindex][zindex] \
                        + newcomp

    print("Plotting the field at z = " + str(z[index]))

    finalfield = planeSelector(zmag, numPointsX, numPointsX, 2, index)
    finalfield = lineSelector(finalfield, numPointsX / 2 - 1)
    plt.plot(y, finalfield)
    plt.show()

if __name__ == "__main__":
    main3()
