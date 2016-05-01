package org.usfirst.frc.team5114.MyRobot2016.auton.commands;

import org.usfirst.frc.team5114.MyRobot2016.Robot;
import org.usfirst.frc.team5114.MyRobot2016.commands.RunLauncher;
import org.usfirst.frc.team5114.MyRobot2016.subsystems.BallLaunch;
import org.usfirst.frc.team5114.MyRobot2016.subsystems.DriveTrain;

public class HalfDriveAutonCmd extends AutonCmd {

	public DriveTrain.Side side;
	
	public HalfDriveAutonCmd(double percentVolt, double seconds, DriveTrain.Side half)
	{
		super(percentVolt, seconds, "Half Drive");
		
		side = half;
		
		requires(Robot.driveTrain);
	}
	
	// Called just before this Command runs the first time
    protected void initialize() {
    	super.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.halfDrive(side, power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return super.isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
    	super.end();
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	super.interrupted();
    	Robot.driveTrain.stop();
    }

}
