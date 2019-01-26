/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.commands.DriveTrainDefault;

import recording.RecordedJoystick;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final MecanumDrive drive;

  private final VictorSP BL;
  private final VictorSP BR;
  private final VictorSP FL;
  private final VictorSP FR;
  
  public AnalogInput lftLineSensor;
  public AnalogInput cntrLineSensor;
  public AnalogInput rghtLineSensor;

  private final Solenoid frontThruster;
  private final DigitalInput frontLimit;
  private final Solenoid backThruster;
  private final DigitalInput backLimit;

  public DriveTrain() {
    BL = RobotMap.bL; //Temporary numbers
    BR = RobotMap.bR;
    FL = RobotMap.fL;
    FR = RobotMap.fR;
    drive = new MecanumDrive(FL, BL, FR, BR);
    lftLineSensor = RobotMap.LftLineSensor;
    cntrLineSensor = RobotMap.CntrLineSensor;
    rghtLineSensor = RobotMap.RghtLineSensor;

    frontThruster = RobotMap.FrontThruster;
    frontLimit = RobotMap.FrontLimit;
    backThruster = RobotMap.BackThruster;
    backLimit = RobotMap.BackLimit;
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
