/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickItUp extends CommandGroup {
  public PickItUp() {
    //Times subject to change
    addSequential(new Flippy2TheGrnd(2));
    addSequential(new FlippyBackUp(2));
  }
}
