/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import frc.robot.commands.dubNoidOn;
import frc.robot.commands.dubNoidOff;
import frc.robot.commands.noidOn;
import frc.robot.commands.noidOff;
import recording.RecordedJoystick;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
  public static RecordedJoystick joystick;

  public OI() {
    joystick = new RecordedJoystick(0);
    //While one holds the X button while a sensor sees the line, the bot aligns
    joystick.whenPressed(1, new noidOn()); //A
    joystick.whenPressed(3, new noidOff()); //X
    joystick.whenPressed(2, new dubNoidOn()); //B
    joystick.whenPressed(4, new dubNoidOff()); //Y
  }
  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
