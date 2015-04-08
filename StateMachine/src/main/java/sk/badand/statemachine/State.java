/*
 * Copyright 2014 Andrej Badinka
 */
package sk.badand.statemachine;

/**
 *
 * @author abadinka
 */
public interface State {
    
    // TODO: introduce Default EndState instead of null
    // TODO: introduce AbstractState that forces to call changeState?
    
    /**
     * Is called at entering this state to machine's current state
     */
    public void enterState(Machine machine);
    
    /**
     * Is called as main execution of state logic.
     * At the end of the implementation it should call machine.changeToState(nextState) or machine.stopMachine
     */
    public void executeState(Machine machine);
    
    /**
     * Is called at exiting from this state
     */
    public void exitState(Machine machine);
    
    // TODO: think of method decideNextState that should force the state implementation to provide the state and execute the changeToState inside machine as private method
}
