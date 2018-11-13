/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RecordedJoystick;
import frc.robot.commands.DriveTrainDefault;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final WPI_TalonSRX left1 = new WPI_TalonSRX(3);
  private final WPI_TalonSRX left2 = new WPI_TalonSRX(4);
  private final WPI_TalonSRX right1 = new WPI_TalonSRX(5);
  private final WPI_TalonSRX right2 = new WPI_TalonSRX(6);
  private final DifferentialDrive diffDrive = new DifferentialDrive(left1, right1);

  public DriveTrain() {
    left2.follow(left1);
    right2.follow(right1);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DriveTrainDefault());
  }

  public void driveAuto(double speed, double rot) {
    diffDrive.arcadeDrive(speed, rot);
  }

  public void drive(RecordedJoystick j) {
    driveAuto(-j.getRawAxis(1), -j.getRawAxis(4));
  }
}

// ok fellas its game time EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
//EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
//EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
