package org.usfirst.frc.team5114.MyRobot2016.auton.commands;

import org.usfirst.frc.team5114.MyRobot2016.Robot;

public class RunLauncherAutonCmd extends AutonCmd {

	public RunLauncherAutonCmd(double percentVolt, double seconds, String name)
	{
		super(percentVolt, seconds, name);
		
		requires(Robot.ballLaunch);
	}
	
	

}
