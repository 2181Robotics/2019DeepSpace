/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.LiftDefault;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;


/**
 * Add your docs here.
 */
public class Lift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //These are limit switches
  private DigitalInput bottomLim;
  private DigitalInput middleLim;
  private DigitalInput topLim;

  private VictorSP liftMotor;

  private Solenoid stem;
  private Solenoid bud;

  public Lift() {
    bottomLim = RobotMap.Bottom;
    middleLim = RobotMap.Middle;
    topLim = RobotMap.Top;

    liftMotor = RobotMap.LiftMotor;
    stem = RobotMap.Stem;
    bud = RobotMap.Bud;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftDefault());
  }

  public void setLiftSpeed(double speed) { // make it so positive is up
    liftMotor.setSpeed(speed);
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
  stem.set(pushed);
  }

  public void setBud(boolean flowering){
    bud.set(flowering);
    }
  
}
