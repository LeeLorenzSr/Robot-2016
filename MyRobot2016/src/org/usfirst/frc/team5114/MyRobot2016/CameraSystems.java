package org.usfirst.frc.team5114.MyRobot2016;

import edu.wpi.first.wpilibj.vision.USBCamera;


/**
 * Manages multiple cameras regardless of actual configuration
 *  	imaqOverlayText
 */
public class CameraSystems {
 	public USBCamera[] cameras;
 	private int maxCameras = 0;
 	private int currentCamera = -1;
 	
 	
 	// Constructor attempts to initialize X cameras 
 	public CameraSystems(int maxCams)
 	{
 		this.maxCameras = maxCams;
 		this.cameras = new USBCamera[maxCams];
 		for( int i=0; i<maxCams; i++ )
 		{
 			try
 			{
 				String camName = "cam"+Integer.toString(i);
 				System.out.println("Initializing "+camName);
 	 			USBCamera newCamera = new USBCamera(camName);
 	 			newCamera.openCamera();
 	 			this.cameras[i] = newCamera; 				
 				System.out.println("Camera "+camName+" found on system and opened");
 			}
 			catch(Exception e)
 			{
 				System.out.println("Camera initialization caused an exception " + e);
 			}
 		}
 	}


	public int getMaxCameras() {
		return maxCameras;
	}

	public USBCamera getCurrentCamera()
	{
		USBCamera retVal = null;
		
		if ( ( this.currentCamera >= 0  ) && (this.currentCamera < this.maxCameras) )
		{
			retVal = this.cameras[this.currentCamera];
		}		
		return retVal;
	}
	
	public int getCurrentCameraIndex() {
		return currentCamera;
	}


	public void setCurrentCameraIndex(int value) {
		
		if ( (value >= 0) && (value < this.maxCameras) && (this.cameras[value] != null ) )
		{
			USBCamera currCam = getCurrentCamera();
			
			if ( currCam != null )
				currCam.stopCapture();
			
			this.currentCamera = value;
			currCam = getCurrentCamera();
			currCam.startCapture();
		}
	}
}