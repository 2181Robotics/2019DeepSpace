package recording;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import edu.wpi.first.wpilibj.command.Command;

public class SaveCommand extends Command {
  private String filename;
  private Saved start;
  private int left;
  private ObjectOutputStream oos;

  public SaveCommand(String filename, Saved start, int length) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    setRunWhenDisabled(true);
    this.filename = filename;
    this.start = start;
    this.left = length;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    try {
        FileOutputStream fos = new FileOutputStream(filename);
        oos = new ObjectOutputStream(fos);
        oos.writeInt(left);
    } catch (Exception e) {
        this.cancel();
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      for (int i=0; i<50; i++) {
          try {
              oos.writeObject(start);
          } catch (Exception e) {
              this.cancel();
              break;
          }
          start = start.next;
          left--;
          if (start==null) {
              break;
          }
      }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (left == 0);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
      try {
          oos.flush();
          oos.close();
      } catch (Exception e) {
          //I have no clue what to do here
      }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
