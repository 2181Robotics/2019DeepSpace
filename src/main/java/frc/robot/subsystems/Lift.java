/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.Lift.LiftDefault;
import recording.RecordedJoystick;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


public class Lift extends Subsystem {
  //These are limit switches
  private DigitalInput bottomLim;
  private DigitalInput middleLim;
  private DigitalInput topLim;

  private WPI_VictorSPX liftMotor1;
  private WPI_VictorSPX liftMotor2;
  private SpeedControllerGroup liftMotors;

  private Solenoid stem;
  private Solenoid bud;

  int DOWNAXIS;
  int UPAXIS;

  public Lift() {
    bottomLim = RobotMap.BottomLim;
    middleLim = RobotMap.MiddleLim;
    topLim = RobotMap.TopLim;

    liftMotor1 = RobotMap.LiftMotor1;
    liftMotor2 = RobotMap.LiftMotor2;
    liftMotors = new SpeedControllerGroup(liftMotor1, liftMotor2);

    stem = RobotMap.Stem;
    bud = RobotMap.Bud;

    DOWNAXIS = 2;
    UPAXIS = 3;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftDefault());
  }

  public void setLiftSpeed(double speed) { // make it so positive is up
    liftMotors.set(speed);
  }

  public void setWithTriggers(RecordedJoystick j) { // make it so positive is up
    if (j.getRawAxis(DOWNAXIS) > .1 && !get("low")){
      setLiftSpeed(-.5 * j.getRawAxis(DOWNAXIS));
      } else if (j.getRawAxis(UPAXIS) > .1 && !get("high")){
      setLiftSpeed(.5 * j.getRawAxis(UPAXIS));
      } else {
      setLiftSpeed(0);
      }
  }

  public boolean get(String choice) {
    if (choice.equals("low")) {
      return bottomLim.get();
    } else if (choice.equals("mid")) {
      return middleLim.get();
    } else if (choice.equals("high")) {
      return topLim.get();
    } else {
      return false;
    }
  }

  public void setStem(boolean pushed){
    if (!pushed) bud.set(false);
    stem.set(pushed);
  }

  public void setBud(boolean flowering){
    if (stem.get()) bud.set(flowering);
  }

  public boolean getBtmLim(){
    return bottomLim.get();
  }
  
}
