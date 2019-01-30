/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveTrainDefault;

import recording.RecordedJoystick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * Add your docs here.
 */
public class DriveTrain extends PIDSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final MecanumDrive drive;
  private final DifferentialDrive drive2;

  private final VictorSP BL;
  private final VictorSP BR;
  private final VictorSP FL;
  private final VictorSP FR;

  private final WPI_TalonSRX L = new WPI_TalonSRX(5);
  private final WPI_TalonSRX R = new WPI_TalonSRX(6);
  
  public AnalogInput lftLineSensor;
  public AnalogInput cntrLineSensor;
  public AnalogInput rghtLineSensor;

  private final Solenoid frontThruster;
  private final DigitalInput frontLimit;
  private final Solenoid backThruster;
  private final DigitalInput backLimit;

  public DriveTrain() {
    super("Drivetrain", .005, .0001, .03); //PID values
    setAbsoluteTolerance(10);
    setInputRange(0, 160);
    setOutputRange(-.5, .5);

    BL = RobotMap.bL;
    BR = RobotMap.bR;
    FL = RobotMap.fL;
    FR = RobotMap.fR;
    drive = new MecanumDrive(FL, BL, FR, BR);
    drive2 = new DifferentialDrive(L, R);
    lftLineSensor = RobotMap.LftLineSensor;
    cntrLineSensor = RobotMap.CntrLineSensor;
    rghtLineSensor = RobotMap.RghtLineSensor;

    frontThruster = RobotMap.FrontThruster;
    frontLimit = RobotMap.FrontLimit;
    backThruster = RobotMap.BackThruster;
    backLimit = RobotMap.BackLimit;
  }

  @Override
  protected double returnPIDInput() {
    return SmartDashboard.getNumber("tape_x", this.getSetpoint());
  }

  @Override
  protected void usePIDOutput(double output) {
    drive(0, 0, -output);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveTrainDefault());
  }

  public void drive(double y, double x, double spin) {
    //drive.driveCartesian(y, x, spin); //mess with values later
    drive2.arcadeDrive(-y, -spin);
  }

  public void drive(RecordedJoystick j) {
    drive(-j.getRawAxis(1), j.getRawAxis(0), j.getRawAxis(4));
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
