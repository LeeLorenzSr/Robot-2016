//RobotBuilder Version: 2.0
//
//This file was generated by RobotBuilder. It contains sections of
//code that are automatically generated and assigned by robotbuilder.
//These sections will be updated in the future when you export to
//Java from RobotBuilder. Do not put any code or make any change in
//the blocks indicating autogenerated code or it will be lost on an
//update. Deleting the comments indicating the section will prevent
//it from being updated in the future.


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
public class GateKeeper extends Subsystem {
 // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

 // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

 // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
 private final CANTalon talon8 = RobotMap.gateKeeperTalon8;
 // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

 // Put methods for controlling this subsystem
 // here. Call these from Commands.
 
 private double gateSpeed = 0.5;
 
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
	 talon8.set(-Robot.oi.controller.getAxis(AxisType.kThrottle) * gateSpeed);
	 SmartDashboard.putNumber("Gate Arm Pos", talon8.getPosition());
	 
//	 ***********************************************************************
//	 ******** ONLY ALLOW FORWARD MOVEMENT WHILE BELOW SOME CONSTANT ********
//	 *********** AND BACKWARD MOVEMENT WHILE ABOVE SOME CONSTANT ***********
//	 ***********************************************************************
//	 if (talon8.getPosition() < someConstant && -Robot.oi.controller.getAxis(AxisType.kThrottle) > 0)
//		 talon8.set(-Robot.oi.controller.getAxis(AxisType.kThrottle) * gateSpeed);
//	 else if (talon8.getPosition() > someOtherConstant && -Robot.oi.controller.getAxis(AxisType.kThrottle) < 0)
//		 talon8.set(-Robot.oi.controller.getAxis(AxisType.kThrottle) * gateSpeed);
 }
 
 public void initDefaultCommand() {
     // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

     // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

     // Set the default command for a subsystem here.
     setDefaultCommand(new DriveGateArm());
 }
}