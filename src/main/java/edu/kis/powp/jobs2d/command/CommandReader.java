package edu.kis.powp.jobs2d.command;

import java.util.List;

public interface CommandReader {
    void read(String text);
    List<DriverCommand> getCommandsList();
    String getName();
}
