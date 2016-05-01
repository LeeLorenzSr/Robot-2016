package org.usfirst.frc.team5114.MyRobot2016.subsystems;

import java.util.Timer;

import org.usfirst.frc.team5114.MyRobot2016.Robot;
import org.usfirst.frc.team5114.MyRobot2016.RobotMap;
import org.usfirst.frc.team5114.MyRobot2016.UltrasonicAnalog;
import org.usfirst.frc.team5114.MyRobot2016.commands.*;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class UltrasonicSensor extends Subsystem
{
    private final UltrasonicAnalog sensor = RobotMap.ultraSensor;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void readSensor()
    {
    	SmartDashboard.putNumber("Sensor Distance", sensor.getInches());
    }
    
    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ReadSensorCmd());
    }
}
