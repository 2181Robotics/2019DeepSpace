/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.CaptainKirkDefault;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import com.ctre.phoenix.motorcontrol.ControlMode;
 
/**
 * Add your docs here.
 */
public class CaptainKirk extends Subsystem {
  private final Solenoid clawNoid;
  private final WPI_TalonSRX flippyRotMotor;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
public CaptainKirk(){
  clawNoid = RobotMap.ClawNoid;
  flippyRotMotor = RobotMap.FlippyRotMotor;
}
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new CaptainKirkDefault());
  }

  public void flippyRotMotorSet(double direction){
    flippyRotMotor.set(ControlMode.PercentOutput, direction);
    /*Make it so you can set the flippRotMotor
    to a certain position that is either ground, vertical, or back
    This will be figured out later */
  }
}
