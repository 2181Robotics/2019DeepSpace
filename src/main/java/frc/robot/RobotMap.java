/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  //Pneumatics
  public static Solenoid FrontThruster = new Solenoid(1, 0);
  public static Solenoid BackThruster = new Solenoid(1, 1);
  public static Solenoid ClawNoid = new Solenoid(1, 2);
  public static Solenoid Stem = new Solenoid(1, 3);
  public static Solenoid Bud = new Solenoid(1, 4);

  //Talons
  public static WPI_TalonSRX FlippyRotMotor = new WPI_TalonSRX(7);//# May be changed later, could use bosch motor

  //Victors
  public static VictorSP bL = new VictorSP(0);
  public static VictorSP bR = new VictorSP(1);
  public static VictorSP fL = new VictorSP(2);
  public static VictorSP fR = new VictorSP(3);
  public static VictorSP LiftMotor = new VictorSP(4);
  
  //Limit Switches
  //public static DigitalInput FrontLimit = new DigitalInput(0);
  //public static DigitalInput BackLimit = new DigitalInput(1);
  public static DigitalInput FrontUltra = new DigitalInput(0);
  public static DigitalInput BackUltra = new DigitalInput(1);
  public static DigitalInput BottomLim = new DigitalInput(2);
  public static DigitalInput MiddleLim = new DigitalInput(3);
  public static DigitalInput TopLim = new DigitalInput(4);

  //Infrared Line Sensors
  public static AnalogInput LftLineSensor = new AnalogInput(0);
  public static AnalogInput CntrLineSensor = new AnalogInput(1);
  public static AnalogInput RghtLineSensor = new AnalogInput(2);

}
