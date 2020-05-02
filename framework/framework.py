from framework.MyArray import MyArray


""" generated source for module Action """
# package: framework
class Action(object):

    def __init__(self):
        """ generated source for method __init__ """
        #  TODO Auto-generated constructor stub

    def getValue(self):
        """ generated source for method getValue """
        return self.value



""" generated source for module Agent """
# package: framework
class Agent(object):
    def createMemento(self):
        """ generated source for method createMemento """
        return AgentMemento()

    def loadFromMemento(self, agentMemento):
        """ generated source for method loadFromMemento """
        return

    def call(self, observationSequence):
        """ generated source for method call """
        return Action()



""" generated source for module AgentFactory """
# package: framework
class AgentFactory(object):

    def __init__(self):
        """ generated source for method __init__ """
        #  TODO Auto-generated constructor stub

    def create(self, buildOrder):
        """ generated source for method create """
        return Agent()

    @classmethod
    def getAgentFactoryUnique(cls):
        """ generated source for method getAgentFactoryUnique """
        AgentFactory.agentFactoryUnique = AgentFactory()
        return cls.agentFactoryUnique



""" generated source for module AgentMemento """
# package: framework
class AgentMemento(object):
    """ generated source for class AgentMemento """



""" generated source for module Builder """
# package: framework
class Builder(object):

    def __init__(self):
        """ generated source for method __init__ """
        #  TODO Auto-generated constructor stub
        self.store = Store.getStoreUnique()
        self.agentFactory = AgentFactory.getAgentFactoryUnique()
        self.environmentFactory = EnvironmentFactory.getEnvironmentFactoryUnique()
        self.trainerFactory = TrainerFactory.getTrainerFactory()
        self.valueFunctionApproximatorFactory = ValueFunctionApproximatorFactory.getvalueFunctionApproximatorFactoryUnique()
        self.rewardGiverFactory = RewardGiverFactory.getRewardGiverFactoryUnique()
        self.currentlyTrainedAgent = None
        self.currentlyTrainedBuildOrder = None

    def build(self, buildOrders):
        """ generated source for method build """
        for buildOrder in buildOrders:
            self.buildOneAgent(buildOrder)

    def buildOneAgent(self, buildOrder):
        """ generated source for method buildOneAgent """
        self.currentlyTrainedBuildOrder = buildOrder
        self.currentlyTrainedAgent = self.agentFactory.create(buildOrder)
        environment = self.environmentFactory.create(buildOrder)
        valueFunctionApproximator = self.valueFunctionApproximatorFactory.create(buildOrder)
        rewardGiver = self.rewardGiverFactory.create(buildOrder)
        trainer = self.trainerFactory.create(self.currentlyTrainedAgent, environment, valueFunctionApproximator, rewardGiver, buildOrder)
        trainer.train(self)
        self.currentlyTrainedBuildOrder = None
        self.currentlyTrainedAgent = None

    def saveAgent(self, trainer):
        """ generated source for method saveAgent """
        agentMemento = self.currentlyTrainedAgent.createMemento()
        storeField = StoreField(trainer.getTimeSimulation(), agentMemento, self.currentlyTrainedBuildOrder)
        self.store.save(storeField)



""" generated source for module BuildOrder """
# package: framework
class BuildOrder(object):

    def __init__(self, nEpoch, nSeq, nHorizonValueOptimization, nIntervalPolicyOptimization, nBatchPolicyOptimization):
        """ generated source for method __init__ """
        #  TODO Auto-generated constructor stub
        self.nEpoch = nEpoch
        self.nSeq = nSeq
        self.nHorizonValueOptimization = nHorizonValueOptimization
        self.nIntervalPolicyOptimization = nIntervalPolicyOptimization
        self.nBatchPolicyOptimization = nBatchPolicyOptimization

    def getnEpoch(self):
        """ generated source for method getnEpoch """
        return self.nEpoch

    def getnSeq(self):
        """ generated source for method getnSeq """
        return self.nSeq

    def getnHorizonValueOptimization(self):
        """ generated source for method getnHorizonValueOptimization """
        return self.nHorizonValueOptimization

    def getnIntervalPolicyOptimization(self):
        """ generated source for method getnIntervalPolicyOptimization """
        return self.nIntervalPolicyOptimization

    def getnBatchPolicyOptimization(self):
        """ generated source for method getnBatchPolicyOptimization """
        return self.nBatchPolicyOptimization



""" generated source for module ClosedLoopSimulator """
# package: framework
class ClosedLoopSimulator(object):

    def __init__(self, environment, agent, buildOrder):
        """ generated source for method __init__ """
        #  TODO Auto-generated constructor stub
        self.agent = agent
        self.environment = environment
        self.buildOrder = buildOrder
        self.observationSequenceLast = ObservationSequence()
        self.actionLast = None
        self.observationSequencePrev = None

    def init(self):
        """ generated source for method init """
        self.observationSequenceLast.clear()
        self.observationSequencePrev = self.observationSequenceLast.copy()
        observation = self.environment.observe()
        self.observationSequenceLast.add(observation)

    def update(self):
        """ generated source for method update """
        self.observationSequencePrev = self.observationSequenceLast.copy()
        self.actionLast = self.agent.call(self.observationSequenceLast)
        self.environment.update(self.actionLast)
        observation = self.environment.observe()
        if len(self.observationSequenceLast) >= self.buildOrder.getnSeq():
            self.observationSequenceLast.remove(0)
        self.observationSequenceLast.add(observation)

    def requestInit(self, trainer):
        """ generated source for method requestInit """
        self.init()
        trainer.addObservationSequence(self.observationSequenceLast.copy())
        trainer.setTimeLastUpdate(-1)
        trainer.setTimeSimulation(-1)

    def requestUpdate(self, trainer):
        """ generated source for method requestUpdate """
        self.update()
        rewardLast = trainer.reward(self.observationSequencePrev, self.actionLast)
        trainer.addObservationSequence(self.observationSequenceLast.copy())
        trainer.addAction(self.actionLast)
        trainer.addReward(rewardLast)
        trainer.addTimeSimulation()



""" generated source for module Environment """
# package: framework
class Environment(object):
    def init(self):
        """ generated source for method init """

    def update(self, action):
        """ generated source for method update """

    def observe(self):
        """ generated source for method observe """
        return Observation()



""" generated source for module EnvironmentFactory """
# package: framework
class EnvironmentFactory(object):

    def __init__(self):
        """ generated source for method __init__ """
        #  TODO Auto-generated constructor stub

    def create(self, buildOrder):
        """ generated source for method create """
        return Environment()

    @classmethod
    def getEnvironmentFactoryUnique(cls):
        """ generated source for method getEnvironmentFactoryUnique """
        EnvironmentFactory.environmentFactoryUnique = EnvironmentFactory()
        return cls.environmentFactoryUnique



""" generated source for module Observation """
# package: framework
class Observation(object):

    def __init__(self):
        """ generated source for method __init__ """
        #  TODO Auto-generated constructor stub

    def getValue(self):
        """ generated source for method getValue """
        return self.value



""" generated source for module ObservationSequence """
# package: framework
class ObservationSequence(MyArray, Observation):
    def copy(self):
        """ generated source for method copy """
        return ObservationSequence(self.clone())



""" generated source for module PolicyOptimizer """
# package: framework
class PolicyOptimizer(object):

    def __init__(self, agent, valueFunctionApproximator, buildOrder):
        """ generated source for method __init__ """
        #  TODO Auto-generated constructor stub
        self.buildOrder = buildOrder

    def train(self, observationSequences):
        """ generated source for method train """
        #  NOT IMPLEMENTED YET

    def requestUpdate(self, trainer):
        """ generated source for method requestUpdate """
        observationSequences = None
        i = 0
        if (trainer.getTimeSimulation() + 1) % self.buildOrder.getnIntervalPolicyOptimization() == 0:
            observationSequences = MyArray()
            #  print "policy optimization was not implemented yet.";
            #  TODO Auto-generated constructor stub
            #  NOT IMPLEMENTED YET
            #  print "policy optimization was not implemented yet.";
            while i < self.buildOrder.getnBatchPolicyOptimization():
                #  TODO Auto-generated constructor stub
                #  NOT IMPLEMENTED YET
                #  print "policy optimization was not implemented yet.";
                observationSequences.add(trainer.getObservationSequence(i))
                i += 1
            self.train(observationSequences)
            trainer.markPolicyUpdate()



""" generated source for module Reward """
# package: framework
class Reward(object):

    def __init__(self):
        """ generated source for method __init__ """
        #  TODO Auto-generated constructor stub

    def getValue(self):
        """ generated source for method getValue """
        return self.value



""" generated source for module RewardGiver """
# package: framework
class RewardGiver(object):
    def evaluate(self, observationSequence, action):
        """ generated source for method evaluate """
        return Reward()



""" generated source for module RewardGiverFactory """
# package: framework
class RewardGiverFactory(object):

    def __init__(self):
        """ generated source for method __init__ """
        #  TODO Auto-generated constructor stub

    def create(self, buildOrder):
        """ generated source for method create """
        return RewardGiver()

    @classmethod
    def getRewardGiverFactoryUnique(cls):
        """ generated source for method getRewardGiverFactoryUnique """
        RewardGiverFactory.rewardGiverFactoryUnique = RewardGiverFactory()
        return cls.rewardGiverFactoryUnique



""" generated source for module Store """
# package: framework
class Store(object):

    def __init__(self):
        """ generated source for method __init__ """
        #  TODO Auto-generated constructor stub

    @classmethod
    def getStoreUnique(cls):
        """ generated source for method getStoreUnique """
        Store.storeUnique = Store()
        return cls.storeUnique

    def save(self, storeField):
        """ generated source for method save """

    def load(self, trainId):
        """ generated source for method load """
        return None



""" generated source for module StoreField """
# package: framework
class StoreField(object):

    def __init__(self, timeSimulation, agentMemento, buildOrder):
        """ generated source for method __init__ """
        #  TODO Auto-generated constructor stub
        self.timeSimulation = timeSimulation
        self.agentMemento = agentMemento
        self.buildOrder = buildOrder

    def getAgentMemento(self):
        """ generated source for method getAgentMemento """
        return self.agentMemento

    def getTimeSimulation(self):
        """ generated source for method getTimeSimulation """
        return self.timeSimulation

    def getBuildOrder(self):
        """ generated source for method getBuildOrder """
        return self.buildOrder



""" generated source for module Trainer """
# package: framework
class Trainer(object):

    def __init__(self, closedLoopSimulator, valueFunctionOptimizer, policyOptimizer, rewardGiver, buildOrder):
        """ generated source for method __init__ """
        #  TODO Auto-generated constructor stub
        self.buildOrder = buildOrder
        self.closedLoopSimulator = closedLoopSimulator
        self.valueFunctionOptimizer = valueFunctionOptimizer
        self.policyOptimizer = policyOptimizer
        self.rewardGiver = rewardGiver
        self.historyActions = MyArray()
        self.historyObservationSequences = MyArray()
        self.historyRewards = MyArray()

    def train(self, builder):
        """ generated source for method train """
        self.historyActions.clear()
        self.historyObservationSequences.clear()
        self.historyRewards.clear()
        self.closedLoopSimulator.requestInit(self)
        #  TODO Auto-generated constructor stub
        i = 0
        while i < self.buildOrder.getnEpoch():
            #  TODO Auto-generated constructor stub
            self.closedLoopSimulator.requestUpdate(self)
            self.valueFunctionOptimizer.requestUpdate(self)
            self.policyOptimizer.requestUpdate(self)
            i += 1

    def addObservationSequence(self, observationSequence):
        """ generated source for method addObservationSequence """
        self.historyObservationSequences.add(observationSequence)

    def addAction(self, action):
        """ generated source for method addAction """
        self.historyActions.add(action)

    def addReward(self, reward):
        """ generated source for method addReward """
        self.historyRewards.add(reward)

    def getObservationSequence(self, idx):
        """ generated source for method getObservationSequence """
        return self.historyObservationSequences.get(idx)

    def getAction(self, idx):
        """ generated source for method getAction """
        return self.historyActions.get(idx)

    def getReward(self, idx):
        """ generated source for method getReward """
        return self.historyRewards.get(idx)

    def reward(self, observationSequence, action):
        """ generated source for method reward """
        return self.rewardGiver.evaluate(observationSequence, action)

    def addTimeSimulation(self):
        """ generated source for method addTimeSimulation """
        self.timeSimulation += 1

    def markPolicyUpdate(self):
        """ generated source for method markPolicyUpdate """
        self.timeLastUpdate = self.timeSimulation

    def getTimeSimulation(self):
        """ generated source for method getTimeSimulation """
        return self.timeSimulation

    def getTimeLastUpdate(self):
        """ generated source for method getTimeLastUpdate """
        return self.timeLastUpdate

    def setTimeSimulation(self, timeSimulation):
        """ generated source for method setTimeSimulation """
        self.timeSimulation = timeSimulation

    def setTimeLastUpdate(self, timeLastUpdate):
        """ generated source for method setTimeLastUpdate """
        self.timeLastUpdate = timeLastUpdate



""" generated source for module TrainerFactory """
# package: framework
class TrainerFactory(object):

    def __init__(self):
        """ generated source for method __init__ """
        #  TODO Auto-generated constructor stub

    @classmethod
    def getTrainerFactory(cls):
        """ generated source for method getTrainerFactory """
        TrainerFactory.trainerFactory = TrainerFactory()
        return cls.trainerFactory

    def create(self, agent, environment, valueFunctionApproximator, rewardGiver, buildOrder):
        """ generated source for method create """
        closedLoopSimulator = ClosedLoopSimulator(environment, agent, buildOrder)
        valueFunctionOptimizer = ValueFunctionOptimizer(valueFunctionApproximator, agent, buildOrder)
        policyOptimizer = PolicyOptimizer(agent, valueFunctionApproximator, buildOrder)
        return Trainer(closedLoopSimulator, valueFunctionOptimizer, policyOptimizer, rewardGiver, buildOrder)



""" generated source for module TrainId """
# package: framework
class TrainId(object):
    """ generated source for class TrainId """



""" generated source for module User """
# package: framework
class User(object):
    """ generated source for class User """



""" generated source for module Value """
# package: framework
class Value(object):

    def __init__(self):
        """ generated source for method __init__ """
        #  TODO Auto-generated constructor stub

    def getValue(self):
        """ generated source for method getValue """
        return self.value



""" generated source for module ValueFunctionApproximator """
# package: framework
class ValueFunctionApproximator(object):
    def call(self, observationSequence):
        """ generated source for method call """
        return Value()



""" generated source for module ValueFunctionApproximatorFactory """
# package: framework
class ValueFunctionApproximatorFactory(object):

    def __init__(self):
        """ generated source for method __init__ """
        #  TODO Auto-generated constructor stub

    def create(self, buildOrder):
        """ generated source for method create """
        return ValueFunctionApproximator()

    @classmethod
    def getvalueFunctionApproximatorFactoryUnique(cls):
        """ generated source for method getvalueFunctionApproximatorFactoryUnique """
        ValueFunctionApproximatorFactory.valueFunctionApproximatorFactoryUnique = ValueFunctionApproximatorFactory()
        return cls.valueFunctionApproximatorFactoryUnique



""" generated source for module ValueFunctionOptimizer """
# package: framework
class ValueFunctionOptimizer(object):

    def __init__(self, valueFunctionApproximator, agent, buildOrder):
        """ generated source for method __init__ """
        #  TODO Auto-generated constructor stub
        self.buildOrder = buildOrder

    def train(self, observationSequences, actions, rewards):
        """ generated source for method train """
        #  NOT IMPLEMENTED YET

    def requestUpdate(self, trainer):
        """ generated source for method requestUpdate """
        timeLastUpdate = trainer.getTimeLastUpdate()
        timeSimulation = trainer.getTimeSimulation()
        observationSequences = None
        actions = None
        rewards = None
        timeBegin = 0
        j = 0
        if timeLastUpdate + self.buildOrder.getnHorizonValueOptimization() <= timeSimulation:
            observationSequences = MyArray()
            actions = MyArray()
            rewards = MyArray()
            timeBegin = timeSimulation - self.buildOrder.getnHorizonValueOptimization() + 1
            #  TODO Auto-generated constructor stub
            #  NOT IMPLEMENTED YET
            while j < self.buildOrder.getnHorizonValueOptimization():
                #  TODO Auto-generated constructor stub
                #  NOT IMPLEMENTED YET
                observationSequences.add(trainer.getObservationSequence(timeBegin + j))
                actions.add(trainer.getAction(timeBegin + j))
                rewards.add(trainer.getReward(timeBegin + j))
                j += 1
            observationSequences.add(trainer.getObservationSequence(timeBegin + self.buildOrder.getnHorizonValueOptimization()))
            self.train(observationSequences, actions, rewards)


