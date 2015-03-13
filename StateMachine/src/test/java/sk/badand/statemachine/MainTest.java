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
        Machine machine = new Machine(new FirstState());
        machine.startMachine();
    }

    class AbstractState implements State {

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

    static class FirstState extends AbstractState {

        @Override
        public void executeState(Machine machine) {
            super.executeState(machine);
            machine.changeToState(new MiddleState1());
        }
    }

    static class MiddleState1 extends AbstractState {

        @Override
        public void executeState(Machine machine) {
            super.executeState(machine);
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
    }

    static class LastState extends AbstractState {

        @Override
        public void executeState(Machine machine) {
            super.executeState(machine);
            machine.stopMachine();
        }
    }
}
