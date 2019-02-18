/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AlignFromIr extends Command {
  //private boolean rawLft;
  private boolean rawCntr;
  //private boolean rawRght;
  public AlignFromIr() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //rawLft = Robot.driveTrain.getLeft();
    rawCntr = Robot.driveTrain.getCntr();
    //rawRght = Robot.driveTrain.getRght();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Reflective ranges TBD
    // if (rawLft) {
    //   Robot.driveTrain.drive(0, -.4, 0);
    // } else if (rawCntr) {
    //   Robot.driveTrain.drive(.4, 0, 0);
    // } else if (rawRght) {
    //   Robot.driveTrain.drive(0, .4, 0);
    // }
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
