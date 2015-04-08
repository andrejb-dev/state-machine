/*
 * Copyright 2014 Andrej Badinka
 */
package sk.badand.statemachine;

/**
 *
 * @author abadinka
 */
public class Machine {
    
    private State currentState = null;

    public Machine(State firstState) {
        assert(firstState != null);
        this.currentState = firstState;
    }
    
    /**
     * Starts the machine by entering the first state and calling the executeState while there is any state present
     */
    public void startMachine() {
        this.currentState.enterState(this);
        while (currentState != null) {            
            this.currentState.executeState(this);
        }
    }
    
    /**
     * Changes the current state of the machine to the provided newState.
     * Before this change the exitState() is called on the old state (currentState).
     * After the change, the enterState is called on the newState.
     */
    public void changeToState(State newState) {
        this.currentState.exitState(this);
        
        this.currentState = newState;        
        if (this.currentState != null) {
            this.currentState.enterState(this);
        }
    }
    
    /**
     * Stops the machine execution by setting the currentState to null
     * Before that the exitState() is called on the last state (currentState).
     * Should be called on the last (finishing) implementation of State interface.
     */
    public void stopMachine(){
        this.currentState.exitState(this);
        this.currentState = null;
    }
}
