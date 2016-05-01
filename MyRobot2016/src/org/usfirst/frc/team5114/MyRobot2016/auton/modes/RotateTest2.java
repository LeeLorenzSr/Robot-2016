package org.usfirst.frc.team5114.MyRobot2016.auton.modes;

import org.usfirst.frc.team5114.MyRobot2016.auton.commands.HalfDriveAutonCmd;
import org.usfirst.frc.team5114.MyRobot2016.auton.commands.RotateRightAutonCmd;
import org.usfirst.frc.team5114.MyRobot2016.subsystems.DriveTrain;

public class RotateTest2 extends AutonCommandGroup
{

	public RotateTest2()
	{
		super("Rotate Test 2");
		
		
//		addSequential(new RotateRightAutonCmd(1.0, 0.5));
		
		for (int i = 0; i < 10; i++)
		{
			addSequential(new HalfDriveAutonCmd(0.5, 0.5, DriveTrain.Side.left));
			addSequential(new HalfDriveAutonCmd(-0.5, 0.5, DriveTrain.Side.right));
		}
		
	}

}
