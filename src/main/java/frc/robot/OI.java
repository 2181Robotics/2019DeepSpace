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

    //MAIN BUTTON LAYOUT - DO NOT CHANGE - TESTING LAYOUT BELOW
    joystick = new RecordedJoystick(0);
    buttonBox = new RecordedJoystick(1);
    joystick.toggleWhenPressed(1, new WallGrab());//A
    joystick.toggleWhenPressed(2, new PickItUp());//B
    joystick.toggleWhenPressed(3, new BudFlowering());//X
    joystick.whileHeld(4, new ExtendStem());//Y
    joystick.whileHeld(5, new SetLift(.6));//LB
    joystick.whileHeld(6, new SetLift(-.6));//RB

    buttonBox.whenPressed(1, new Deliver("cargo"));//THESE
    buttonBox.whenPressed(2, new Deliver("low"));//SUBJECT
    buttonBox.whenPressed(3, new Deliver("middle"));//TO
    buttonBox.whenPressed(4, new Deliver("top"));//CHANGE
    buttonBox.whenPressed(5, new Climb());
    buttonBox.whenPressed(6, new InterruptAll());//This is assuming we have a claw mechanism

  }
}
