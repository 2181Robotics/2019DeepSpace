/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.SetLiftSpeed;
import frc.robot.commands.ZuccIn;
import frc.robot.commands.ZuccOut;
import recording.RecordAuto;
import recording.RecordedJoystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  RecordedJoystick joystick;

  JoystickButton record;
  RecordAuto rc;

  public OI() {
    joystick = Robot.joystick;
    joystick.whileHeld(3, new ZuccIn());
    joystick.whileHeld(4, new ZuccOut());

    joystick.whileHeld(5, new SetLiftSpeed(.6));
    joystick.whileHeld(6, new SetLiftSpeed(-.6));
    //Recording is done with a regular JoystickButton because a replay could trigger it's own
    //function if it was passed through a RecordedJoystick
    record = new JoystickButton(joystick.getJoystick(), 1);
    rc = new RecordAuto(joystick, Robot.to_record, Robot.so);
    record.toggleWhenPressed(rc);
  }

  public boolean isSaving() {
    return rc.isSaving();
  } 
  
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

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
