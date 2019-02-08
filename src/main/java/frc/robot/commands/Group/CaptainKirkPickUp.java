/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Group;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.CaptainKirk.ClawOpen;
import frc.robot.commands.CaptainKirk.Flippy2TheGrnd;
import frc.robot.commands.CaptainKirk.FlippyBackUp;

public class CaptainKirkPickUp extends CommandGroup {
  public CaptainKirkPickUp() {
    //Times subject to change
    //Vision finds hatch on ground?
    addSequential(new Flippy2TheGrnd(2));
    addSequential(new ClawOpen());
    addSequential(new FlippyBackUp(2));
  }
}
