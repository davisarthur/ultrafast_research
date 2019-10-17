# Davis Arthur
# 10-11-2019
# Auburn University

# science constants
import scipy.constants

# plotting libraries
import matplotlib.pyplot as plt
import numpy as np

# Calculates the magnetic field of a single wire at a given point, current, and
# length of the wire
def singleWire(x0, y0, z0, current, length):

    co = scipy.constants.mu_0 * current / (4 * scipy.constants.pi)

    lsq = x0 ** 2 + z0 ** 2

    # if the magnetic field blows up a certain point, call the magnetic field zero
    if (lsq == 0) or (x0 == 0 or z0 == 0) and (y0 > 0 and y0 < length):
        magfield = (0, 0)
    # otherwise calculate the magnetic field
    else:
        # part of the magnetic field that depends soley on y-position
        genMag = y0 / (lsq * (y0 ** 2 + lsq) ** (1 / 2.0)) \
            - (y0 - length) / (lsq * ((y0 - length) ** 2 + lsq) ** (1 / 2.0))
        # magnetic field in the x-direction
        xMag = co * z0 * genMag
        # magnetic field in the y-direction
        zMag = - co *  x0 * genMag

        magfield = (xMag, zMag)

    return magfield

# takes 3d array of points and returns 2d plane at desired index
# axisNum - 0 (y-z plane), 1 (x-z plane), 2 (x-y plane)
def planeSelector(array, arraySize, axisNum, index):
    output = []
    if axisNum == 0:
        output = array[index]
    elif axisNum == 1:
        for i in range(arraySize):
            output.append(array[i][index])
    elif axisNum == 2:
        for i in range(arraySize):
            intermediate = []
            for j in range(arraySize):
                intermediate.append(array[i][j][index])
            output.append(intermediate)

    return output

# generate array of 3D points
x = []
y = []
z = []

numPoints = 100

# create x-y plane at z = 10
for i in range(numPoints):
    x.append(i - (numPoints / 2.0))
    y.append(i - (numPoints / 2.0))
    #z.append(i - (numPoints / 2.0))
    z = 1.0

zmag = [[[0 for k in range(numPoints)] for j in range(numPoints)] \
    for i in range(numPoints)]

for xindex in range(numPoints):
    for yindex in range(numPoints):
        #for zindex in range(numPoints):
        xcord = x[xindex]
        ycord = y[yindex]
        #zcord = z[zindex]
        zcord = z
        #zmag[xindex][yindex][zindex] = singleWire(xcord, ycord, zcord, \
            #1.0, 25.0)[1]
        zmag[xindex][yindex] = singleWire(xcord, ycord, zcord, \
            1.0, 25.0)[1]

print("Plotting the field at z = 1.0")

#finalfield = planeSelector(zmag, numPoints, 2, 52)
plt.contourf(x, y, zmag, 100, cmap = 'BrBG')
plt.colorbar()
plt.show()
