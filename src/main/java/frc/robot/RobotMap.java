/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Ultrasonic;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  //Pneumatics
  public static Solenoid FrontThruster = new Solenoid(7, 0);
  public static Solenoid BackThruster = new Solenoid(7, 1);
  public static Solenoid ClawNoid = new Solenoid(7, 2);
  public static Solenoid Stem = new Solenoid(7, 3);
  public static Solenoid Bud = new Solenoid(7, 4);

  //Talons
  public static WPI_TalonSRX FlippyRotMotor = new WPI_TalonSRX(8);//# May be changed later, could use bosch motor

  //Victors
  public static WPI_VictorSPX fL = new WPI_VictorSPX(1);
  public static WPI_VictorSPX fR = new WPI_VictorSPX(2);
  public static WPI_VictorSPX bL = new WPI_VictorSPX(3);
  public static WPI_VictorSPX bR = new WPI_VictorSPX(4);
  public static WPI_VictorSPX LiftMotor1 = new WPI_VictorSPX(5);
  public static WPI_VictorSPX LiftMotor2 = new WPI_VictorSPX(6);
  
  //Limit Switches
  //public static DigitalInput FrontLimit = new DigitalInput(0);
  //public static DigitalInput BackLimit = new DigitalInput(1);
  public static AnalogInput FrontUltra = new AnalogInput(1);
  public static AnalogInput BackUltra = new AnalogInput(2);
  public static DigitalInput BottomLim = new DigitalInput(2);
  public static DigitalInput MiddleLim = new DigitalInput(3);
  public static DigitalInput TopLim = new DigitalInput(4);

  //AnalogInput: Pressure Gauge & Infrared Line Sensors
  //public static AnalogInput LftLineSensor = new AnalogInput(0);
  public static AnalogInput CntrLineSensor = new AnalogInput(0);
  //public static AnalogInput RghtLineSensor = new AnalogInput(2);
  public static AnalogInput PressureGauge = new AnalogInput(3);


}
