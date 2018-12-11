/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoReplay extends Command {
  public String filename;

  public AutoReplay(String filename) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.filename = "/home/lvuser/"+filename;
    Robot.joystick.Load(this.filename);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.joystick.setStart(this.filename);
    Robot.joystick.startReplay();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.joystick.isDone();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.joystick.stopReplay();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.joystick.stopReplay();
  }
}
