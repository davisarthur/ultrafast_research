import math
import scipy.constants # science constants
import matplotlib.pyplot as plt # plotting libraries
import numpy as np

from SingleLoop import *

    def __init__(self, current, radius, numwires, numloops, height):
        self.current = current
        self.radius = radius
        self.numwires = numwires
        self.numloops = numloops
        self.height = height
        self.loops = createLoops(self)

    def createLoops(self):
        bottomZ = - height / 2.0
        btwnloops = float(height) / self.numloops
        output = []
        for i in range(numLoops):
            z = bottomZ + i * btwnloops
            newloop = loop(self.current, self.radius, self.numwires, z)
            output.append(newloop)
        return output
