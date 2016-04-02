package org.usfirst.frc.team5114.MyRobot2016.auton.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team5114.MyRobot2016.subsystems.*;

/**
 *
 */
public class AutonCommandGroup extends CommandGroup
{
	private String cmdGroupName;
	
	public AutonCommandGroup(String cmdName)
	{
		cmdGroupName = cmdName;
	}
	
	public String toString()
	{
		return cmdGroupName + " Auton Command Group";
	}
}