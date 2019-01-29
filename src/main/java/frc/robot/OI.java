/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import recording.RecordedJoystick;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
  public static RecordedJoystick joystick;
  public static RecordedJoystick buttonBox;
  public OI() { //All buttons subject to change
    joystick = new RecordedJoystick(0);
    buttonBox = new RecordedJoystick(1);
    joystick.toggleWhenPressed(1, new ExtendStem());//A
    joystick.toggleWhenPressed(2, new BudFlowering());//B
    joystick.toggleWhenPressed(3, new ClawControl());//X
    //joystick.whileHeld(4, new ());//Y
    joystick.whileHeld(5, new FlippyRotMotorBack());//LB - back is out of frame
    joystick.whileHeld(6, new FlippyRotMotorForward());//RB - forward is into frame
    joystick.whenPressed(8, new Climb());//Start button
    joystick.whenPressed(7, new TapeAlign());
    joystick.whenReleased(7, new DriveTrainDefault());

    buttonBox.whenPressed(1, new Deliver("cargo"));//THESE
    buttonBox.whenPressed(2, new Deliver("low"));//SUBJECT
    buttonBox.whenPressed(3, new Deliver("middle"));//TO
    buttonBox.whenPressed(4, new Deliver("top"));//CHANGE
    buttonBox.whenPressed(5, new AlignFromInfrared());
    buttonBox.whenPressed(6, new PickItUp());//This is assuming we have a claw mechanism

  }
}
