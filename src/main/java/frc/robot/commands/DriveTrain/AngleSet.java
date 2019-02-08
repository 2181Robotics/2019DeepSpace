/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class AngleSet extends Command {
  private final boolean first;

  public AngleSet(boolean first) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
    this.first = first;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (first) {
      double ratio = SmartDashboard.getNumber("tape_ratio", 1);
      if (ratio>1) {
        Robot.driveTrain.setSetpoint(50);
      } else {
        Robot.driveTrain.setSetpoint(110);
      }
    } else {
      Robot.driveTrain.setSetpoint(80);
    }
    Robot.driveTrain.getPIDController().reset();
    Robot.driveTrain.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (first) {
      double ratio = SmartDashboard.getNumber("tape_ratio", 1);
      if (ratio>.9&&ratio<1.1) {
        Robot.driveTrain.setSetpoint(80);
      } else {
        if (ratio>1) {
          Robot.driveTrain.setSetpoint(50);
        } else {
          Robot.driveTrain.setSetpoint(110);
        }
      }
    } else {
      Robot.driveTrain.setSetpoint(80);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.driveTrain.onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
