/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.MainDefault;
import edu.wpi.first.wpilibj.AnalogInput;
/**
 * Add your docs here.
 */
public class Main extends Subsystem {
  private AnalogInput pressureGauge;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Main(){
    pressureGauge = new AnalogInput(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new MainDefault());
  }

  public int getPressure(){
    return pressureGauge.getValue();
  }
}
