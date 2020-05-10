'''
Created on 2020/05/05

@author: ukai
'''

import sys
sys.path.append("../../concrete")
sys.path.append("../../framework")

from ConcAgent import ConcAgent
from ConcAgentFactory import ConcAgentFactory
from ConcBuildOrder import ConcBuildOrder
from ConcEnvrionmentFactory import ConcEnvironmentFactory
from ConcMyLogger import ConcMyLogger
from ConcRewardGiverFactory import ConcRewardGiverFactory
from ConcStore import ConcStore
from ConcTrainerFactory import ConcTrainerFactory
from ConcValueFunctionApproximatorFactory import ConcValueFunctionApproximatorFactory
from MyArray import MyArray
from framework import Builder


if __name__ == "__main__":

    builder = Builder(ConcStore()
            , ConcAgentFactory()
            , ConcEnvironmentFactory()
            , ConcTrainerFactory()
            , ConcValueFunctionApproximatorFactory()
            , ConcRewardGiverFactory()
            , ConcMyLogger())
    
    buildOrders = MyArray()
        
    nTrials = 100

    for k1 in range(nTrials):
        buildOrder = ConcBuildOrder(nIteration=2**16
                    , nSeq=1
                    , nHorizonValueOptimization=2
                    , nIntervalPolicyOptimization=10
                    , nBatchPolicyOptimization=2**5
                    , nSaveInterval=2**8
                    , description="test %d/%d" % ((k1+1), nTrials)
                    , nLevers=4)
        
        buildOrders.add(buildOrder)
        
    builder.build(buildOrders)
    
