/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Group;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DriveTrain.AngleSet;
import frc.robot.commands.DriveTrain.DriveToLine;

public class VisionTapeAlign extends CommandGroup {
  public VisionTapeAlign() {
    addSequential(new AngleSet(true));
    addSequential(new DriveToLine());
  }
}
