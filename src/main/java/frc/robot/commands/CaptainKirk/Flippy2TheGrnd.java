/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.CaptainKirk;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class Flippy2TheGrnd extends TimedCommand {

  public Flippy2TheGrnd(double timeout) {
    super(timeout);
    // Use requires() here to declare subsystem dependencies
    requires(Robot.captainKirk);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.captainKirk.flippyRotMotorSet(-.6);
  }

  // Called once after timeout
  @Override
  protected void end() {
    Robot.captainKirk.flippyRotMotorSet(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.captainKirk.flippyRotMotorSet(0);
  }
}
