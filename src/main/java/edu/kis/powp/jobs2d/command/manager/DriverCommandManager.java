package edu.kis.powp.jobs2d.command.manager;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.CommandReader;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.JSONCommand;
import edu.kis.powp.observer.Publisher;

import java.util.Iterator;
import java.util.List;

/**
 * Driver command Manager.
 */
public class DriverCommandManager {
    private DriverCommand currentCommand = null;
    private CommandReader commandReader = null;
    private Publisher changePublisher = new Publisher();

    public synchronized void setCurrentCommandFromString(String text){
        commandReader.read(text);
        this.setCurrentCommand(this.getCommandReader().getCommandsList(), this.getCommandReader().getName());
    }

    public synchronized void setCommandReader(CommandReader commandReader){
        this.commandReader = commandReader;
    }

    public synchronized CommandReader getCommandReader(){
        return this.commandReader;
    }

    /**
     * Set current command.
     *
     * @param commandList list of commands representing a compound command.
     * @param name        name of the command.
     */
    public synchronized void setCurrentCommand(List<DriverCommand> commandList, String name) {
        setCurrentCommand(new ICompoundCommand() {

            List<DriverCommand> driverCommands = commandList;

            @Override
            public void execute(Job2dDriver driver) {
                driverCommands.forEach((c) -> c.execute(driver));
            }

            @Override
            public Iterator<DriverCommand> iterator() {
                return driverCommands.iterator();
            }

            @Override
            public String toString() {
                return name;
            }
        });

    }

    /**
     * Return current command.
     *
     * @return Current command.
     */
    public synchronized DriverCommand getCurrentCommand() {
        return currentCommand;
    }

    /**
     * Set current command.
     *
     * @param commandList Set the command as current.
     */
    public synchronized void setCurrentCommand(DriverCommand commandList) {
        this.currentCommand = commandList;
        changePublisher.notifyObservers();
    }

    public synchronized void clearCurrentCommand() {
        currentCommand = null;
    }

    public synchronized String getCurrentCommandString() {
        if (getCurrentCommand() == null) {
            return "No command loaded";
        } else
            return getCurrentCommand().toString();
    }

    public Publisher getChangePublisher() {
        return changePublisher;
    }
}
