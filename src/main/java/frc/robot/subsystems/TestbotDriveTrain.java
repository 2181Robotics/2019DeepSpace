/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RecordedJoystick;
import frc.robot.commands.DriveTrainDefault;

/**
 * Add your docs here.
 */
public class TestbotDriveTrain extends DriveTrain {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final Talon left1 = new Talon(0);
  private final Talon left2 = new Talon(1);
  private final Talon right1 = new Talon(2);
  private final Talon right2 = new Talon(3);
  private final DifferentialDrive diffDrive = new DifferentialDrive(left1, right1);
  private final DifferentialDrive diffDrive2 = new DifferentialDrive(left2, right2);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveTrainDefault());
  }

  public void driveAuto(double speed, double rot) {
    diffDrive.arcadeDrive(speed, rot);
    diffDrive2.arcadeDrive(speed, rot);
  }

  public void drive(RecordedJoystick j) {
    driveAuto(-j.getRawAxis(1), -j.getRawAxis(4));
  }
}
