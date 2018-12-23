/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package recording;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class RecordAuto extends Command {

  private RecordedJoystick joystick;
  private SendableChooser<String> choice;
  private SaveCommand sc;

  public RecordAuto(RecordedJoystick joystick, SendableChooser<String> choice) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.joystick = joystick;
    this.choice = choice;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    joystick.startRecord();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    joystick.stepRecord();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    joystick.stopRecord();
    String filepath = choice.getSelected();
    joystick.updateReplay(filepath, joystick.start);
    sc = new SaveCommand(filepath, joystick.start, joystick.total);
    sc.start();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

  public boolean isSaving() {
    if (sc!=null) return sc.isRunning();
    else return false;
  }
}
