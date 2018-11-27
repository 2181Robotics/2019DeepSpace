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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RecordedJoystick;
import frc.robot.commands.DriveTrainDefault;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final WPI_TalonSRX left1;
  private final WPI_TalonSRX left2;
  private final WPI_TalonSRX right1;
  private final WPI_TalonSRX right2;
  private final DifferentialDrive diffDrive;

  public DriveTrain() {
    left1 = new WPI_TalonSRX(3);
    left2 = new WPI_TalonSRX(4);
    right1 = new WPI_TalonSRX(5);
    right2 = new WPI_TalonSRX(6);
    diffDrive = new DifferentialDrive(left1, right1);
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
    SmartDashboard.putString("drive", "called");
    double speed = -j.getRawAxis(1);
    SmartDashboard.putNumber("speed", speed);
    if (Math.abs(speed)<.2) {
      speed = 0;
    }
    double rotation = -j.getRawAxis(4);
    if (Math.abs(rotation)<.2) {
      rotation = 0;
    }
    driveAuto(speed, rotation);
  }
}


