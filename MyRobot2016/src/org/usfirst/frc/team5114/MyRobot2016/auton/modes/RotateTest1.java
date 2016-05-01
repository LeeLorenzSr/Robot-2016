package org.usfirst.frc.team5114.MyRobot2016.auton.modes;

import org.usfirst.frc.team5114.MyRobot2016.auton.commands.DriveSouthAutonCmd;
import org.usfirst.frc.team5114.MyRobot2016.auton.commands.HalfDriveAutonCmd;
import org.usfirst.frc.team5114.MyRobot2016.auton.commands.RotateRightAutonCmd;
import org.usfirst.frc.team5114.MyRobot2016.subsystems.DriveTrain;

public class RotateTest1 extends AutonCommandGroup
{

	public RotateTest1()
	{
		super("Rotate Test 1");
		
		addSequential(new RotateRightAutonCmd(1.0, 2.0));
		
	}

}
