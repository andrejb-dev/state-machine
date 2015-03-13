# state-machine
Simple Java library implementing finite state machine design pattern

Interface State defines methods:
* enterState(Machine machine) - serves for custom state initialization
* executeState(Machine machine) - serves for main state logic to execute, there should be decided the next state and called the machine.changeToState
* exitState(Machine machine) - serves to custom state finalization, there should be called machine.stopMachine in the last state or the machine will run endlessly


Class Machine is the main implementation. It holds the current state, is instantiated with entry state and provides three public methods:
* startMachine() - starts the execution, initializes the first state with enterState and afterwards executeState while there is any current state
* changeToState(State newState) - serves to transform from one state to another. Should be called at the end of executeState method of State object. Finishes the current state with exitState, assigns the new state as the current one and calles the enterState on the new current state.
* stopMachine() - finishes the execution, calls the exitState on the current state and assigns it to null so the machine will stop
