package org.usfirst.frc.team5114.MyRobot2016.commands;

import org.usfirst.frc.team5114.MyRobot2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SelectRearCamera extends Command {

	public SelectRearCamera() {
		// TODO Auto-generated constructor stub
	}

	public SelectRearCamera(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public SelectRearCamera(double timeout) {
		super(timeout);
		// TODO Auto-generated constructor stub
	}

	public SelectRearCamera(String name, double timeout) {
		super(name, timeout);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
//		Robot.setCamera(Robot.CameraLoc.Back);

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
