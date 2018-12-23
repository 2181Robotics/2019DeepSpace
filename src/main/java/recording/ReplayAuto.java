/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package recording;

import edu.wpi.first.wpilibj.command.Command;

public class ReplayAuto extends Command {

  private RecordedJoystick joystick;
  private String filename;
  private SaveOrganizer group;

  public ReplayAuto(String filename, RecordedJoystick joystick, SaveOrganizer group) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.filename = filename;
    this.joystick = joystick;
    this.group = group;
    if (!group.exists(filename)) {
        Saved start = joystick.makePlaceHolder();
        group.add(filename, start);
        LoadCommand lc = new LoadCommand(filename, start);
        lc.start();
    }
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    group.set(filename, joystick);
    joystick.startReplay();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return joystick.isDone();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    joystick.stopReplay();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
