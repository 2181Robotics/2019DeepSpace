/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import recording.RecordedJoystick;
import frc.robot.commands.AlignFromInfrared;
import frc.robot.commands.ClawOpen;
import frc.robot.commands.Climb;
import frc.robot.commands.ExtendOutta;
import frc.robot.commands.FlippyRotMotorBack;
import frc.robot.commands.FlippyRotMotorForward;
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
    joystick.whenPressed(1, new ClawOpen());//A
    joystick.whenPressed(2, new ClawClose());//B
    //Put in command for the lift mechanism, TBD - joystick.whileHeld(3, new ());//X
    buttonBox.whileHeld(4, new ExtendOutta());//Y
    joystick.whileHeld(5, new FlippyRotMotorBack());//LB - back is out of frame
    joystick.whileHeld(6, new FlippyRotMotorForward());//RB - forward is into frame
    joystick.whenPressed(8, new Climb());//Start button

    buttonBox.whenPressed(1, new Deliver("low"));//SUBJECT
    buttonBox.whenPressed(2, new Deliver("middle"));//TO
    buttonBox.whenPressed(3, new Deliver("top"));//CHANGE
    buttonBox.whenPressed(4, new AlignFromInfrared());
    buttonBox.whenPressed(5, new PickItUp());//This is assuming we have a claw mechanism

  }
}
