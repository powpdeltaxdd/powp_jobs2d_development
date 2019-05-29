package edu.kis.powp.jobs2d.command;

import java.util.Iterator;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;

public class ComplexCommand implements ICompoundCommand {
	
	private int posX, posY;
	private List<DriverCommand> list;
	
	public ComplexCommand(int posX, int posY, List<DriverCommand> list) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.list = list;
	}
	
	@Override
	public Iterator<DriverCommand> iterator(){
		return list.iterator();
	}

	@Override
	public void execute(Job2dDriver driver) {
		driver.operateTo(posX, posY);		
	}

}
