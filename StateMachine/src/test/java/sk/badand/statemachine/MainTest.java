/*
 * Copyright 2014 Andrej Badinka
 */
package sk.badand.statemachine;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abadinka
 */
public class MainTest {

    private static final Logger LOG = Logger.getLogger(AbstractState.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // initializing the machine with the first state
        Machine machine = new Machine(new FirstState());
        // starting the machine, no need to do anything else, the states themselves are responsible to control the machine's lifetime
        machine.startMachine();
    }

    /**
     * Providing abstract implementation of state with default behaviour (here just logging the executed method)
     * No need to use such abstract implementation in real projects, but its recomended for reusing common functionality
     */
    static class AbstractState implements State {

        @Override
        public final void enterState(Machine machine) {
            LOG.log(Level.INFO, "{0}: Entering state..", getClass().getSimpleName());
        }

        @Override
        public void executeState(Machine machine) {
            LOG.log(Level.INFO, "{0}: Executing state..", getClass().getSimpleName());
        }

        @Override
        public final void exitState(Machine machine) {
            LOG.log(Level.INFO, "{0}: Exiting state..", getClass().getSimpleName());
        }

    }

    /**
     * Implementing first state. Overrides only executeState, other methods are sufficiently implemented for this sample
     * First state needs to be known before the machine starts.
     */
    static class FirstState extends AbstractState {

        @Override
        public void executeState(Machine machine) {
            super.executeState(machine); // only logging
            // the first state knows, what should follow it, so MiddleState1 is initialized and changed to.
            machine.changeToState(new MiddleState1());
        }
    }

    static class MiddleState1 extends AbstractState {

        @Override
        public void executeState(Machine machine) {
            super.executeState(machine); // only logging
            // any state can provide extensive logic in selecting the next state, based on random or planned atributes/parameters
            double random = Math.random();
            if (random > 0.6) {
                machine.changeToState(new MiddleState1());
            } else if (random > 0.3) {
                machine.changeToState(new MiddleState2());
            } else {
                machine.changeToState(new LastState());
            }
        }
    }

    static class MiddleState2 extends MiddleState1 {
        // the states can be extended to reuse or override the whole class or some parts
    }

    /**
     * For now, null as newState can be used to stop the machine, thats not so pretty
     * Last or Finish state shoul be insted introduced and used to stop the machine.
     * Call to stopMachine still executes the last exitState of the current state
     */
    static class LastState extends AbstractState {

        @Override
        public void executeState(Machine machine) {
            super.executeState(machine); // only logging
            machine.stopMachine();
        }
    }
}
