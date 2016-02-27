package org.usfirst.frc.team5114.MyRobot2016;

import edu.wpi.first.wpilibj.AnalogInput;

public class UltrasonicAnalog extends AnalogInput
{
	private final double CONVERSION_FACTOR = 254.0 / 4095.0;
	
	public UltrasonicAnalog(int analogChanel)
	{
		super(analogChanel);
	}
	
	public double getInches()
	{
		return getValue() * CONVERSION_FACTOR;
	}
	
	public double getRaw()
	{
		AnalogInput.setGlobalSampleRate(20);
		
		setOversampleBits(4);
		setAverageBits(2);
		
		return getAverageVoltage();
	}
}