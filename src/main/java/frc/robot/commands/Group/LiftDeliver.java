/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Group;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.Lift.BudClose;
import frc.robot.commands.Lift.GoTo;
import frc.robot.commands.Lift.StemExtend;
import frc.robot.commands.Lift.StemRetract;

public class LiftDeliver extends CommandGroup {
  private class Prepare extends CommandGroup {
    public Prepare(String level) {
      addParallel(new GoTo(level));
      addParallel(new VisionTapeAlign());   
    }
  }

  public LiftDeliver(String level) {
    addSequential(new Prepare(level));
    addSequential(new StemExtend(1.5));//TBD - Time for extend
    addSequential(new BudClose());
    addSequential(new StemRetract());
    addSequential(new GoTo("low"));
  }
}
