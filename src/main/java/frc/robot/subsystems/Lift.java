/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.LiftDefault;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */

public class Lift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private final WPI_TalonSRX motor1 = new WPI_TalonSRX(7);
  private final WPI_TalonSRX motor2 = new WPI_TalonSRX(8);
  private final SpeedControllerGroup motors = new SpeedControllerGroup(motor1, motor2);

  private final DigitalInput bottom = new DigitalInput(0);
  private final DigitalInput top = new DigitalInput(1);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new LiftDefault());
  }

  public void setMotors(double speed) {
    if (speed>0&&!bottom.get() || speed<0&&!top.get()) {
      motors.set(speed);
    } else {
      motors.set(0);
    }
  }


}
