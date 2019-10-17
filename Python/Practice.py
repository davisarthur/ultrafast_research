import matplotlib.pyplot as plt
import numpy as np
import random
import math
from SingleWire2 import planeSelector

# def f(x, y):
#     f_x = x ** 2 / 4.0 + y ** 2 / 36.0 #+ z ** 2 / 1.0
#     return f_x
#
# # generate array of 3D points
# x = []
# y = []
# z = []
#
# numPoints = 100

# # create x-y plane at z = 10
# for i in range(numPoints):
#     x.append(i - (numPoints / 2.0))
#     y.append(i - (numPoints / 2.0))
#     z.append(i - (numPoints / 2.0))
#
# test = [[[0 for k in range(numPoints)] for j in range(numPoints)] \
#     for i in range(numPoints)]
#
# for xindex in range(numPoints):
#     for yindex in range(numPoints):
#         #for zindex in range(numPoints):
#         xcord = x[xindex]
#         ycord = y[yindex]
#         #zcord = z[zindex]
#         test[xindex][yindex] = f(xcord, ycord)
#
# #printfunc = planeSelector(test, numPoints, 2, numPoints / 2 + 2)
#
# #finalfield = planeSelector(zmag, numPoints, 2, 52)
# #plt.contourf(x, y, test, 100, cmap = 'BrBG')
# #plt.colorbar()
# #plt.show()

print(math.sin(math.radians(15)))
