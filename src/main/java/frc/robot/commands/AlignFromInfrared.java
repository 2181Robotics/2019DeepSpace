/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AlignFromInfrared extends Command {
  private double rawLft;
  private double rawCntr;
  private double rawRght;
  public AlignFromInfrared() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    rawLft = Robot.driveTrain.getLeft();
    rawCntr = Robot.driveTrain.getCntr();
    rawRght = Robot.driveTrain.getRght();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Reflective ranges TBD
    if (rawLft < 1500) {
      Robot.driveTrain.drive(0, -.4, 0);
    } else if (rawCntr < 1500) {
      Robot.driveTrain.drive(.4, 0, 0);
    } else if (rawRght < 1500) {
      Robot.driveTrain.drive(0, .4, 0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.drive(0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
