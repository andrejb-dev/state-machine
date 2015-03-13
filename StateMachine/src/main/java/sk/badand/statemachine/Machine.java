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
     * Starts the machine by entering and executing the first state
     */
    public void startMachine() {
        this.currentState.enterState(this);
        while (currentState != null) {            
            this.currentState.executeState(this);
        }
    }
    
    public void changeToState(State newState) {
        this.currentState.exitState(this);
        
        this.currentState = newState;        
        if (this.currentState != null) {
            this.currentState.enterState(this);
        }
    }
    
    public void stopMachine(){
        this.currentState.exitState(this);
        this.currentState = null;
    }
}
