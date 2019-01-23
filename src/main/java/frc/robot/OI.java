/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import recording.RecordedJoystick;
import frc.robot.commands.AlignFromInfrared;
import frc.robot.commands.ClawClose;
import frc.robot.commands.ClawOpen;
import frc.robot.commands.Climb;
import frc.robot.commands.FlippyRotMotorBack;
import frc.robot.commands.FlippyRotMotorForward;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
  public static RecordedJoystick joystick;
  public OI() { //All buttons subject to change
    joystick = new RecordedJoystick(0);
    joystick.whenPressed(1, new ClawOpen());
    joystick.whenPressed(2, new ClawClose());
    joystick.whileHeld(3, new AlignFromInfrared()); //While one holds the X button while a sensor sees the line, the bot aligns
    joystick.whileHeld(5, new FlippyRotMotorBack());
    joystick.whileHeld(6, new FlippyRotMotorForward());
    joystick.whenPressed(8, new Climb());
    
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
