package org.usfirst.frc.team5114.MyRobot2016.auton.modes;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team5114.MyRobot2016.auton.commands.DriveSouthAutonCmd;
import org.usfirst.frc.team5114.MyRobot2016.auton.commands.DropBackArmAutonCmd;
import org.usfirst.frc.team5114.MyRobot2016.auton.commands.LiftBackArmAutonCmd;
import org.usfirst.frc.team5114.MyRobot2016.subsystems.*;

/**
 *
 */
public class ChevalDeFrise extends AutonCommandGroup
{

    public ChevalDeFrise()
    {
    	super("Cheval de Frise");
    	
    	addSequential(new DriveSouthAutonCmd(0.3, 1.2));
    	
    	addParallel(new DriveSouthAutonCmd(0.1, 0.4));
    	addSequential(new DropBackArmAutonCmd(0.5, 0.5));
    	
    	addParallel(new DropBackArmAutonCmd(0.2, 0.3));
    	addSequential(new DriveSouthAutonCmd(0.5, 1.5));
    	
    	addSequential(new DriveSouthAutonCmd(0.3,  1.0));
    	
    	addSequential(new LiftBackArmAutonCmd(0.5, 0.5));
    } 
}
