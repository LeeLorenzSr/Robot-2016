package org.usfirst.frc.team5114.MyRobot2016.subsystems;

import java.awt.Robot;

import javax.management.timer.Timer;

import org.usfirst.frc.team5114.MyRobot2016.RobotMap;
import org.usfirst.frc.team5114.MyRobot2016.commands.*;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
*
*/
public class BallIntake extends Subsystem
{
	 private final VictorSP victorSP7 = (VictorSP) RobotMap.ballIntakeVictor7;
	 
	 private final CANTalon outerIntake = RobotMap.ballIntakeTalon9;
	
	 private double intakeSpeed = 0.5, shootSpeed = 1.0;
	 private double outerIntakeSpd = 0.5, outerIntakeShootSpd;
	 
	 // Put methods for controlling this subsystem
	 // here. Call these from Commands.
	 
	 public double getIntakeSpeed() { return intakeSpeed; }
	 public double getLowGoalSpeed() { return shootSpeed; }
	 
	 public void intakeBall(double percentVolt)
	 {
		 victorSP7.set(-percentVolt);
	 }
	 
	 public void shootLow(double percentVolt)
	 {
		 victorSP7.set(percentVolt);
	 }
	 
	 public void startIntake()
	 {
		 intakeSpeed = SmartDashboard.getNumber("Intake Speed");
		 victorSP7.set(-intakeSpeed);
		 
		 outerIntakeSpd = SmartDashboard.getNumber("Outer Intake Speed");
		 outerIntake.set(-outerIntakeSpd);
	 }
	 
	 public void startShoot()
	 {
		 shootSpeed = SmartDashboard.getNumber("Low Goal Speed");
		 victorSP7.set(shootSpeed);
		 
		 outerIntakeShootSpd = SmartDashboard.getNumber("Outer Intake Shoot Speed");
		 outerIntake.set(outerIntakeShootSpd);
	 }
	 
	 public void stop()
	 {
		 victorSP7.set(0.0);
		 outerIntake.set(0.0);
	 }
	 
	 public void initDefaultCommand()
	 {	
	     // Set the default command for a subsystem here.
	     // setDefaultCommand(new MySpecialCommand());
	 }
}