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
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
/**
 * Add your docs here.
 */
public class Zucc extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final Talon left = new Talon(0);
  private final Talon right = new Talon(1);

  private final DifferentialDrive diffDrive = new DifferentialDrive(left, right);

  public void SuccOfTheZucc(){
    diffDrive.arcadeDrive(0,0.5);

  }

  public void UnSuccOfTheZucc(){
    diffDrive.arcadeDrive(0,-0.5);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
