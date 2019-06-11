package edu.kis.powp.jobs2d.command;

import java.util.Iterator;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;

public class ComplexCommand implements ICompoundCommand {
	
	private List<DriverCommand> list;
	private String name;
	
	public ComplexCommand(List<DriverCommand> list, String name) {
		this.list = list;
		this.name = name;
	}
	
	@Override
	public Iterator<DriverCommand> iterator(){
		return list.iterator();
	}

	@Override
	public void execute(Job2dDriver driver) {
		list.forEach((c) -> c.execute(driver));
	}
	
    @Override
    public String toString() {
        return name;
    }
}
