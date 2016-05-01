package org.usfirst.frc.team5114.MyRobot2016.subsystems;

import javax.management.timer.Timer;

import org.usfirst.frc.team5114.MyRobot2016.Robot;
import org.usfirst.frc.team5114.MyRobot2016.RobotMap;
import org.usfirst.frc.team5114.MyRobot2016.commands.*;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
*
*/
public class GateKeeper extends Subsystem
{
	 private final CANTalon talon8 = RobotMap.gateKeeperTalon8;
	
	 // Put methods for controlling this subsystem
	 // here. Call these from Commands.
	 
	 private double gateSpeed = 0.5;
	 
	 public void liftGateArm(double percentVolt)
	 {
		 // This is correct
		 talon8.set(percentVolt);
	 }
	 
	 public void dropGateArm(double percentVolt)
	 {
		 // This is correct
		 talon8.set(-percentVolt);
	 }
	 
	 public void lift()
	 {
		 gateSpeed = SmartDashboard.getNumber("Gate Speed");
		 talon8.set(gateSpeed);
	 }
	 
	 public void drop()
	 {
		 gateSpeed = SmartDashboard.getNumber("Gate Speed");
		 talon8.set(-gateSpeed);
	 }
	 
	 public void stop()
	 {
		 talon8.set(0.0);
	 }
	 
	 public void driveGateArm()
	 {
	//	 double someConstant, someOtherConstant;
		 double speed = -Robot.oi.controller.getAxis(AxisType.kThrottle) * gateSpeed;
		 
		 talon8.set(speed);
		 SmartDashboard.putNumber("Gate Arm Pos", talon8.getPosition());
		 	 
	//	 ***********************************************************************
	//	 ******** ONLY ALLOW FORWARD MOVEMENT WHILE BELOW SOME CONSTANT ********
	//	 *********** AND BACKWARD MOVEMENT WHILE ABOVE SOME CONSTANT ***********
	//	 ***********************************************************************
	//	 if (speed < 0.0 && talon8.getPosition() > someConstant)
	//		 talon8.set(speed);
	//	 else if (speed > 0.0 && talon8.getPosition() < someOtherConstant)
	//		 talon8.set(speed);
		 
		 SmartDashboard.putNumber("Gate Input", speed);
		 SmartDashboard.putNumber("Gate Output", talon8.get());
	 }
	 
	 public void initDefaultCommand()
	 {
	     // Set the default command for a subsystem here.
	     setDefaultCommand(new DriveGateArm());
	 }
}