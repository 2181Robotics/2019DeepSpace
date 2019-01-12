/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.commands.DriveTrainDefault;
import recording.RecordedJoystick;
import edu.wpi.first.wpilibj.AnalogInput;

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

  public DriveTrain() {
    BL = new WPI_TalonSRX(0); //Temp numbers
    BR = new WPI_TalonSRX(1);
    FL = new WPI_TalonSRX(2);
    FR = new WPI_TalonSRX(3);
    drive = new MecanumDrive(FL, BL, FR, BR);
    lftLineSensor = new AnalogInput(0);
    cntrLineSensor = new AnalogInput(1);
    rghtLineSensor = new AnalogInput(2);
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

  protected int getLeft(int rawLft){
    int rawLft = lftLineSensor.getValue();
    return rawLft;
  }

  protected int getCntr(int rawCntr){
    int rawCntr = cntrLineSensor.getValue();
    return rawCntr;
  }

  protected int getRght(int rawRght){
    int rawRght = rghtLineSensor.getValue();
    return rawRght;
  }
}
