/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class CenterOnDisk extends Command {
  private static errout eo = new errout();
  private static PIDController pid = new PIDController(.009, .0005, .003, 0, eo, eo, .070);

  public CenterOnDisk() {
    pid.setAbsoluteTolerance(10);
    pid.setOutputRange(-.45, .45);
    pid.setInputRange(0, 160);
    pid.setSetpoint(80);
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
    //PIDController pid = new PIDController(Kp, Ki, Kd, Kf, source, output);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    SmartDashboard.putBoolean("Centering", true);
    pid.reset();
    pid.enable();
    // int x = (int) SmartDashboard.getNumber("disk_x", -1);
    // int y = (int) SmartDashboard.getNumber("disk_y", -1);
    // double turn = 0;
    // if (x>0 && x<80) {
    //   turn = Math.pow((80.0-x)/40, .3)/2;
    // } else if (x>80) {
    //   turn = -Math.pow((x-80.0)/40, .3)/2;
    // }
    // SmartDashboard.putNumber("centering_turn", turn);
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //return (pid.onTarget() && Math.abs(pid.get())<.1);
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    SmartDashboard.putBoolean("Centering", false);
    pid.disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

class errout implements PIDSource, PIDOutput {
  public double pidGet() {
    double x = SmartDashboard.getNumber("disk_x", -1);
    if (x>0) {
      return x;
    } else {
      return 80;
    }
  }

  public PIDSourceType getPIDSourceType() {
    return PIDSourceType.kDisplacement;
  }

  public void setPIDSourceType(PIDSourceType pidSource) {
    return;
  }

  public void pidWrite(double output) {
    SmartDashboard.putNumber("pid_output", output);
    Robot.driveTrain.driveAuto(0, output);
  }
}