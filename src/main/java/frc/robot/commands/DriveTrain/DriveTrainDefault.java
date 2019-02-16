/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrainDefault extends Command {
  private boolean lftSensingWhite;
  private boolean cntrSensingWhite;
  private boolean rghtSensingWhite;
  private double rawLft;
  private double rawCntr;
  private double rawRght;
  private int pressure;
  public DriveTrainDefault() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveTrain.drive(OI.joystick);
    rawLft = Robot.driveTrain.getLeft();
    rawCntr = Robot.driveTrain.getCntr();
    rawRght = Robot.driveTrain.getRght();
    pressure = Robot.driveTrain.getPressure();
    //Reflective ranges TBD
    if (rawLft < 1500) {
      lftSensingWhite = true;
      SmartDashboard.putBoolean("Left Sensor Can See Line", lftSensingWhite);
    } else {
      lftSensingWhite = false;
      SmartDashboard.putBoolean("Left Sensor Can See Line", lftSensingWhite);
    }

    if (rawCntr < 1500) {
      cntrSensingWhite = true;
      SmartDashboard.putBoolean("Center Sensor Can See Line", cntrSensingWhite);
    } else {
      cntrSensingWhite = false;
      SmartDashboard.putBoolean("Center Sensor Can See Line", cntrSensingWhite);
    }

    if (rawRght < 1500) {
      rghtSensingWhite = true;
      SmartDashboard.putBoolean("Right Sensor Can See Line", rghtSensingWhite);
    } else {
      rghtSensingWhite = false;
      SmartDashboard.putBoolean("Right Sensor Can See Line", rghtSensingWhite);
    }

    SmartDashboard.putNumber("Pressure", pressure);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
