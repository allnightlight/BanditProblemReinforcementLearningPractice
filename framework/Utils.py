import random
import string

from framework.MyArray import MyArray
import numpy as np


class Utils(object):

    @classmethod
    def generateRandomString(cls, length):
        
        randomString = "".join(random.choices(string.ascii_lowercase + string.ascii_uppercase + string.digits, k=length))
        
        return randomString        
        

    @classmethod
    def myRandomRrandint(cls, nLow, nHigh, nSize):
        
        return np.random.randint(low=nLow, high=nHigh, size=nSize) # (*,)
