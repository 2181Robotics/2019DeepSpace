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
  private boolean buttons[];
  private boolean joys[];

  public ReplayAuto(String filename, RecordedJoystick joystick) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.filename = filename;
    this.joystick = joystick;
    if (!joystick.exists(filename)) {
        Saved start = joystick.makePlaceHolder();
        joystick.add(filename, start);
        LoadCommand lc = new LoadCommand(filename, start);
        lc.start();
    }
  }

  public ReplayAuto(String filename, RecordedJoystick joystick, boolean buttons[], boolean joys[]) {
    this(filename, joystick);
    this.buttons = buttons;
    this.joys = joys;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    joystick.set(filename);
    if (buttons != null) joystick.startReplay(buttons, joys);
    else joystick.startReplay();
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
