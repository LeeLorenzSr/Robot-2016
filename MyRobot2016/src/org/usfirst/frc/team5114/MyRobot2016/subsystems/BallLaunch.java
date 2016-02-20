// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc.team5114.MyRobot2016.subsystems;

import org.usfirst.frc.team5114.MyRobot2016.Robot;
import org.usfirst.frc.team5114.MyRobot2016.RobotMap;
import org.usfirst.frc.team5114.MyRobot2016.commands.*;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class BallLaunch extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final CANTalon talon5 = RobotMap.ballLaunchTalon5;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private double percentVoltage = 0.5;
    
    public double getPercentVoltage() { return percentVoltage; }
    
    public double getSpeed() { return talon5.get(); }
    
    public void printEncVel() {
    	System.out.println("spd(rpm)???: " + talon5.getSpeed() * 3 / 5);
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void runLauncher() {
    	percentVoltage = SmartDashboard.getNumber("Launch Power", 0.5);
    	talon5.set(percentVoltage);
    	
    	System.out.println("runLauncher called");
    }
    
    public void shoot() {
    	double trgRPM = SmartDashboard.getNumber("RPM(Launcher)");
    	double motorOut = talon5.getOutputVoltage() / talon5.getBusVoltage();
    	
    	talon5.setF(1023 / (trgRPM * 4096 / 600));
    	
    	do {
	    	System.out.print("\tout:");
	    	System.out.print(motorOut);
	    	System.out.print("\tspd:");
	    	System.out.print(talon5.getSpeed());
	    	
	    	talon5.changeControlMode(TalonControlMode.Speed);
	    	talon5.set(trgRPM);
	    	
	    	System.out.print("\terr:");
	    	System.out.print(talon5.getClosedLoopError());
	    	System.out.print("\ttrg:");
	    	System.out.print(trgRPM);
	    	
	    	System.out.println();
    	} while (!Robot.oi.controllerButton3.get());
    }
    
    public void stop() {
    	talon5.set(0.0);
    }
}