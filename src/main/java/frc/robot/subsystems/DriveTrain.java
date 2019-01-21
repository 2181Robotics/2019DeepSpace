/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.commands.DriveTrainDefault;
import recording.RecordedJoystick;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private final WPI_TalonSRX BL;
  private final WPI_TalonSRX BR;
  private final WPI_TalonSRX FL;
  private final WPI_TalonSRX FR;
  private final MecanumDrive drive;
  public AnalogInput lftLineSensor;
  public AnalogInput cntrLineSensor;
  public AnalogInput rghtLineSensor;

  private final Solenoid frontThruster;
  private final DigitalInput frontLimit;
  private final Solenoid backThruster;
  private final DigitalInput backLimit;

  public DriveTrain() {
    BL = new WPI_TalonSRX(0); //Temporary numbers
    BR = new WPI_TalonSRX(1);
    FL = new WPI_TalonSRX(2);
    FR = new WPI_TalonSRX(3);
    drive = new MecanumDrive(FL, BL, FR, BR);
    lftLineSensor = new AnalogInput(0);
    cntrLineSensor = new AnalogInput(1);
    rghtLineSensor = new AnalogInput(2);

    frontThruster = new Solenoid(1, 0);
    frontLimit = new DigitalInput(0);
    backThruster = new Solenoid(1, 1);
    backLimit = new DigitalInput(1);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveTrainDefault());
  }

  public void drive(double y, double x, double spin) {
    drive.driveCartesian(y, x, spin); //mess with values later
  }

  public void drive(RecordedJoystick j) {
    drive(j.getRawAxis(4), j.getRawAxis(3), j.getRawAxis(1));
  }

  public double getLeft(){
    return lftLineSensor.getValue();
  }

  public double getCntr(){
    return cntrLineSensor.getValue();
  }

  public double getRght(){
    return rghtLineSensor.getValue();
  }

  public void setFrontThruster(boolean value) {
    frontThruster.set(value);
  }

  public void setBackThruster(boolean value) {
    backThruster.set(value);
  }

  public boolean getFrontLimit() {
    return frontLimit.get();
  }

  public boolean getBackLimit() {
    return backLimit.get();
  }
}
