// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc.team5114.MyRobot2016;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

import org.usfirst.frc.team5114.MyRobot2016.auton.versions.ChevalDeFrise;
import org.usfirst.frc.team5114.MyRobot2016.auton.versions.Drawbridge;
import org.usfirst.frc.team5114.MyRobot2016.auton.versions.LowBar;
import org.usfirst.frc.team5114.MyRobot2016.auton.versions.Moat;
import org.usfirst.frc.team5114.MyRobot2016.auton.versions.Portcullis;
import org.usfirst.frc.team5114.MyRobot2016.auton.versions.Ramparts;
import org.usfirst.frc.team5114.MyRobot2016.auton.versions.RockWall;
import org.usfirst.frc.team5114.MyRobot2016.auton.versions.RoughTerrain;
import org.usfirst.frc.team5114.MyRobot2016.auton.versions.SallyPort;
import org.usfirst.frc.team5114.MyRobot2016.commands.*;
import org.usfirst.frc.team5114.MyRobot2016.subsystems.*;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.VisionException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    SendableChooser autoChooser;
    
    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static BallLaunch ballLaunch;
    public static BallIntake ballIntake;
    public static GateKeeper gateKeeper;
    public static BackArm backArm;
    public static UltrasonicSensor ultrasonicSensor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public enum CameraLoc { None, Front, Back };

    
 	//camera stuff 
 	static boolean cameraSwitchPressedLastTime = false; 
 	static Image img = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0); 
 	static boolean rearCam = false; // stores whether the front camera is on 
 	static CameraLoc camLocation = CameraLoc.Front;
 
 	public static USBCamera cameraFront; 
 	public static USBCamera cameraBack; 
 
 	public static CameraServer camServer = CameraServer.getInstance(); 
    /****************************************************/
    
 	public static void cameraInit()
 	{
		try 
		{ 
 			cameraFront = new USBCamera("cam0"); 
			cameraBack = new USBCamera("cam1"); 
 			cameraFront.openCamera(); 
 			cameraBack.openCamera(); 
 			cameraFront.startCapture(); // startCapture so that it doesn't try to take a picture before the camera is on 
 			camServer.setQuality(100); 
 		} 
		catch (VisionException e) 
		{ 
 			System.out.println("VISION EXCEPTION ~ " + e); 
 		}	
 	}
    
    public static void setCameraFeed(CameraLoc cameraLocation)
    {
    	if (camLocation != cameraLocation)
    	{
    		// Stop old feed
    		switch (camLocation)
    		{
    		case Front:
    			cameraFront.stopCapture();
    			break;
    		case Back:
    			cameraBack.stopCapture();
    			break;
    		}
    		
    		// Start new feed
    		switch (cameraLocation)
        	{
        	case Front:
        		cameraFront.startCapture();
        		break;
        	case Back:
        		cameraBack.startCapture();
        		break;
        	}
    	}
    	
    	camLocation = cameraLocation;
    }
    
    private void setImage() {
		switch (camLocation)
        {
        case Front:
        	cameraFront.getImage(img);
        	camServer.setImage(img);
        	break;
        case Back:
        	cameraBack.getImage(img);
        	camServer.setImage(img);
        	break;
        }
	}
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        ballLaunch = new BallLaunch();
        ballIntake = new BallIntake();
        gateKeeper = new GateKeeper();
        backArm = new BackArm();

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        autoChooser.addDefault("Low Bar", new LowBar());
        autoChooser.addObject("Cheval de Frise [DOES NOTHING]", new ChevalDeFrise());
        autoChooser.addObject("Drawbridge [DOES NOTHING]", new Drawbridge());
        autoChooser.addObject("Moat [DOES NOTHING]", new Moat());
        autoChooser.addObject("Portcullis [DOES NOTHING]", new Portcullis());
        autoChooser.addObject("Ramparts [DOES NOTHING]", new Ramparts());
        autoChooser.addObject("Rock Wall [DOES NOTHING]", new RockWall());
        autoChooser.addObject("Rough Terrain [DOES NOTHING]", new RoughTerrain());
        autoChooser.addObject("Sally Port [DOES NOTHING]", new SallyPort());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        
        // Initialize PID values for talon motor controllers
        RobotMap.driveTrainTalon1.setF(0.0);
        RobotMap.driveTrainTalon1.setPID(0, 0, 0);
        
        RobotMap.driveTrainTalon2.changeControlMode(TalonControlMode.Follower);
        RobotMap.driveTrainTalon2.set(RobotMap.driveTrainTalon1.getDeviceID());
        
        RobotMap.driveTrainTalon3.setF(0.0);
        RobotMap.driveTrainTalon3.setPID(0, 0, 0);
        
        RobotMap.driveTrainTalon4.changeControlMode(TalonControlMode.Follower);
        RobotMap.driveTrainTalon4.set(RobotMap.driveTrainTalon3.getDeviceID());
        
        RobotMap.ballLaunchTalon5.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        RobotMap.ballLaunchTalon5.reverseSensor(false);
        RobotMap.ballLaunchTalon5.configNominalOutputVoltage(+0, -0);
        RobotMap.ballLaunchTalon5.configPeakOutputVoltage(+12.0, 0.0);
        RobotMap.ballLaunchTalon5.setProfile(0);
        RobotMap.ballLaunchTalon5.setF(0.0211); //calculated off of max RPM 7100
        RobotMap.ballLaunchTalon5.setPID(0, 0, 0);
        
        RobotMap.gateKeeperTalon8.enableBrakeMode(true);
        RobotMap.gateKeeperTalon8.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
        RobotMap.gateKeeperTalon8.setPosition(0);
        RobotMap.gateKeeperTalon8.reverseSensor(false);
        RobotMap.gateKeeperTalon8.configNominalOutputVoltage(+0, -0);
        RobotMap.gateKeeperTalon8.configPeakOutputVoltage(+12.0, 0.0);
        
        // Necessary code for camera feed
        cameraInit();
        
        // Smart Dashboard initiation
        SmartDashboard.putNumber("Launch Power", 1.0);
        SmartDashboard.putNumber("Intake Speed", 0.5);
        SmartDashboard.putNumber("RPM(Launcher)", 0.0);
        SmartDashboard.putNumber("Low Goal Speed", 1.0);
        SmartDashboard.putNumber("Gate Speed", 0.5);
        SmartDashboard.putNumber("Drive Power", 0.8);
        SmartDashboard.putNumber("Sensor Distance", 0);
        SmartDashboard.putNumber("Back Arm Speed", -0.2);
        SmartDashboard.putString("Camera", "Front");
        SmartDashboard.putNumber("Gate Arm Pos", 0.0);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	autonomousCommand = (Command) autoChooser.getSelected();
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        driveTrain.setPower(0.8);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        //setImage();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
