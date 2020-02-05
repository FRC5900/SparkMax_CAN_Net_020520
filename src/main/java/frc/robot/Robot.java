/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;  

public class Robot extends TimedRobot {
  private Joystick m_stick;
  private static final int M1deviceID = 2;
  private static final int M2deviceID = 3;
  private CANSparkMax m_motor;
  private CANSparkMax m_motor2;
  private CANEncoder m_encoder;
  private CANEncoder m_encoder2;


  @Override
  public void robotInit() {
    // initialize SPARK MAX
    m_motor = new CANSparkMax(M1deviceID, MotorType.kBrushless);
    m_motor.restoreFactoryDefaults();
    m_motor2 = new CANSparkMax(M2deviceID, MotorType.kBrushless);
    m_motor2.restoreFactoryDefaults();
    /**
     * The RestoreFactoryDefaults method can be used to reset the configuration parameters
     * in the SPARK MAX to their factory default state. If no argument is passed, these
     * parameters will not persist between power cycles
     */
   

    /**
    * In order to read encoder values an encoder object is created using the 
    * getEncoder() method from an existing CANSparkMax object
    */
    m_encoder = m_motor.getEncoder();
    m_encoder.setPosition(0);

    m_encoder2 = m_motor2.getEncoder();
    m_encoder2.setPosition(0);

    m_stick = new Joystick(0);
  }

  @Override
  public void teleopPeriodic() {
    // set the motor output based on jostick position
    m_motor.set(m_stick.getY());
    m_motor2.set(m_stick.getX());


  }
  
  @Override 
  public void robotPeriodic() {


     /**
     * There are several useful bus measurements you can get from the SparkMax.
     * This includes bus voltage (V), output current (A), Applied Output
     * (duty cycle), and motor temperature (C)
     */
    double busVoltage = m_motor.getBusVoltage();
    double current = m_motor.getOutputCurrent();
    double appliedOut = m_motor.getAppliedOutput();
    double temperature = m_motor.getMotorTemperature();
    // Open SmartDashboard when your program is running to see the values
    SmartDashboard.putNumber("M1 Bus Voltage", busVoltage);
    SmartDashboard.putNumber("M1 Current", current);
    SmartDashboard.putNumber("M1 Applied Output", appliedOut);
    SmartDashboard.putNumber("M1 Motor Temperature", temperature);

 /**
     * There are several useful bus measurements you can get from the SparkMax.
     * This includes bus voltage (V), output current (A), Applied Output
     * (duty cycle), and motor temperature (C)
     */
    double busVoltage2 = m_motor2.getBusVoltage();
    double current2 = m_motor2.getOutputCurrent();
    double appliedOut2 = m_motor2.getAppliedOutput();
    double temperature2 = m_motor2.getMotorTemperature();
    // Open SmartDashboard when your program is running to see the values
    SmartDashboard.putNumber("M2 Bus Voltage", busVoltage2);
    SmartDashboard.putNumber("M2 Current", current2);
    SmartDashboard.putNumber("M2 Applied Output", appliedOut2);
    SmartDashboard.putNumber("M2 Motor Temperature", temperature2);


    SmartDashboard.putNumber("Encoder Position", m_encoder.getPosition());
    SmartDashboard.putNumber("Encoder Velocity", m_encoder.getVelocity());
    SmartDashboard.putNumber("Encoder Position", m_encoder2.getPosition());
    SmartDashboard.putNumber("Encoder Velocity", m_encoder2.getVelocity());
  }
}