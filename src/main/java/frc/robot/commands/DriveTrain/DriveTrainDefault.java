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
    lftSensingWhite = Robot.driveTrain.getLeft();
    cntrSensingWhite = Robot.driveTrain.getCntr();
    rghtSensingWhite = Robot.driveTrain.getRght();
    pressure = Robot.driveTrain.getPressure();
    SmartDashboard.putNumber("Pressure", pressure);
    //Reflective ranges TBD
    SmartDashboard.putBoolean("Left Sensor Can See Line", lftSensingWhite);
    SmartDashboard.putBoolean("Center Sensor Can See Line", cntrSensingWhite);
    SmartDashboard.putBoolean("Right Sensor Can See Line", rghtSensingWhite);
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
