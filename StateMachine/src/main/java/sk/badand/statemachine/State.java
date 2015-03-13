/*
 * Copyright 2014 Andrej Badinka
 */
package sk.badand.statemachine;

/**
 *
 * @author abadinka
 */
public interface State {
    
    public void enterState(Machine machine);
    public void executeState(Machine machine);
    public void exitState(Machine machine);
}
