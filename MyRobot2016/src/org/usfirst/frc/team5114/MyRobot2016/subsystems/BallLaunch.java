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

import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team5114.MyRobot2016.Robot;
import org.usfirst.frc.team5114.MyRobot2016.RobotMap;
import org.usfirst.frc.team5114.MyRobot2016.commands.*;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class BallLaunch extends Subsystem {
	
    private final CANTalon talon5 = RobotMap.ballLaunchTalon5;
    private final Relay lightRelay = RobotMap.lightRelay;
    
    public static enum Power {high, low};

    private double highPowerPctVolt = 1.0;
    private double lowPowerPctVolt = 0.8;
    
    public void setLightRelay(boolean value)
    {
    	if (value)
    		lightRelay.set(Value.kForward);
    	else
    		lightRelay.set(Value.kOff);
    }
    
    public boolean getLightRelay()
    {
    	boolean result;
    	
    	result = lightRelay.get().equals(Value.kForward);
    	
    	return result;
    }
    
    public double getPercentVoltage() { return highPowerPctVolt; }
    
    public double getSpeed() { return talon5.get(); }
    
    public void printEncVel()
    {
    	System.out.println("spd(rpm)???: " + talon5.getSpeed() * 3 / 5);
    }
    
    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public void runLauncher(Power power) {
    	// Turn flash light on
    	lightRelay.set(Relay.Value.kForward);
   
    	if (power == Power.high)
    	{
    	    highPowerPctVolt = SmartDashboard.getNumber("High Launch Power", 1.0);
    	    talon5.changeControlMode(TalonControlMode.PercentVbus);
    	    talon5.set(highPowerPctVolt);
    	}
    	else if (power == Power.low)
    	{
    		lowPowerPctVolt = SmartDashboard.getNumber("Low Launch Power", 0.8);
    	    talon5.changeControlMode(TalonControlMode.PercentVbus);
    	    talon5.set(lowPowerPctVolt);
    	}
    	
    	/*
    	double motorOut = talon5.getOutputVoltage() / talon5.getBusVoltage();
    	
    	System.out.print("\tout:");
    	System.out.print(motorOut);
    	System.out.print("\tspd:");
    	System.out.print(talon5.getSpeed());
    	
    	System.out.println();
    	*/
    }
    
    public void set(double percentVolt)
    {
    	talon5.set(percentVolt);
    }
    
    public void shoot()
    {
    	double trgRPM = SmartDashboard.getNumber("RPM(Launcher)");
    	double motorOut;
    	
    	//// Turn flash light on
    	//lightRelay.set(Value.kOn);
    	
    	Timer timer = new Timer();
    	
    	// There is talk about removing this PID loop system...
    	// Others having confidence in driver ability and power solely based on voltage percent...
    	
    	do
    	{
    		motorOut = talon5.getOutputVoltage() / talon5.getBusVoltage();
    		
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
    	} while (!Robot.oi.controllerButton5.get() && talon5.getSpeed() < 7000.0);
    	
    	if (!Robot.oi.controllerButton5.get())
    	{
    		Robot.ballIntake.startIntake();
    		
    		try
    		{
    			timer.wait(1000);
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    		
    		Robot.ballIntake.stop();
    		stop();
    	}
    }
    
    public void stop()
    {
    	// Turn flash light off
    	lightRelay.set(Relay.Value.kOff);
    	
    	talon5.set(0.0);
    }
}