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
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.commands.DriveTrain.DriveTrainDefault;

import recording.RecordedJoystick;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;

public class DriveTrain extends PIDSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final MecanumDrive drive;
  private final DifferentialDrive drive2;

  private final WPI_VictorSPX BL;
  private final WPI_VictorSPX BR;
  private final WPI_VictorSPX FL;
  private final WPI_VictorSPX FR;
  
  public AnalogInput lftLineSensor;
  public AnalogInput cntrLineSensor;
  public AnalogInput rghtLineSensor;
  private final double sensorThreshold = 1500;

  public AnalogInput pressureGauge;

  private final Solenoid frontThrusterDown;
  private final Solenoid frontThrusterUp;
  //private final DigitalInput frontLimit;
  private final AnalogInput frontUltra;
  private final Solenoid backThrusterDown;
  private final Solenoid backThrusterUp;
  //private final DigitalInput backLimit;
  private final AnalogInput backUltra;
  private final int ultraThreshold = 1000; //change later

  public DriveTrain() {
    super("Drivetrain", .005, .0001, .03); //PID values
    setAbsoluteTolerance(10);
    setInputRange(0, 160);
    setOutputRange(-.5, .5);

    BL = RobotMap.bL;
    BL.setInverted(false);
    BR = RobotMap.bR;
    BR.setInverted(false);
    FL = RobotMap.fL;
    FL.setInverted(false);
    FR = RobotMap.fR;
    FR.setInverted(false);
    drive = new MecanumDrive(FL, BL, FR, BR);
    drive.setSafetyEnabled(false);
    drive.setDeadband(.15);
    drive.setMaxOutput(.75);
    drive2 = new DifferentialDrive(FL, FR);
    lftLineSensor = RobotMap.LftLineSensor;
    cntrLineSensor = RobotMap.CntrLineSensor;
    rghtLineSensor = RobotMap.RghtLineSensor;

    pressureGauge = RobotMap.PressureGauge;

    frontThrusterDown = RobotMap.FrontThrusterDown;
    frontThrusterUp = RobotMap.FrontThrusterUp;
    //frontLimit = RobotMap.FrontLimit;
    frontUltra = RobotMap.FrontUltra;
    backThrusterDown = RobotMap.BackThrusterDown;
    backThrusterUp = RobotMap.BackThrusterUp;
    //backLimit = RobotMap.BackLimit;
    backUltra = RobotMap.BackUltra;
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
    drive.driveCartesian(-x, y, spin); //mess with values later
    SmartDashboard.putNumber("Pressure", getPressure());
  }

  public void drive(double speed, double rot) {
    drive2.arcadeDrive(speed, rot);
    SmartDashboard.putNumber("Pressure", getPressure());
  }

  public void drive(RecordedJoystick j) {
    drive(-j.getRawAxis(1), -j.getRawAxis(0), j.getRawAxis(4));
  }

  public boolean getLeft(){
     return lftLineSensor.getValue()<sensorThreshold;
  }

  public boolean getCntr(){
    return cntrLineSensor.getValue()<sensorThreshold;
  }

  public boolean getRght(){
     return rghtLineSensor.getValue()<sensorThreshold;
  }

  public void setFrontThruster(boolean value) {
    if (value) {
      frontThrusterUp.set(false);
      frontThrusterDown.set(true);
    } else {
      frontThrusterDown.set(false);
      frontThrusterUp.set(true);
    }
  }

  public void setBackThruster(boolean value) {
    if (value) {
      backThrusterUp.set(false);
      backThrusterDown.set(true);
    } else {
      backThrusterDown.set(false);
      backThrusterUp.set(true);
    }
  }
  
  public boolean getFrontUltra() {
    return frontUltra.getValue()<ultraThreshold;
  }

  public boolean getBackUltra() {
    return backUltra.getValue()<ultraThreshold;
  }

  public int getPressure() {
    return pressureGauge.getValue();
  }
  
}
