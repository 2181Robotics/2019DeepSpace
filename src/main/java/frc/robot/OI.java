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

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
  public static RecordedJoystick joystick;
  public OI() { //All buttons subject to change
    joystick = new RecordedJoystick(0);
    joystick.whileHeld(1, new ClawOpen());//A
    joystick.whileHeld(2, new ExtendOutta());//B
    joystick.whileHeld(3, new AlignFromInfrared());//X, while sensor sees line, bot aligns
    joystick.whileHeld(5, new FlippyRotMotorBack());//LB
    joystick.whileHeld(6, new FlippyRotMotorForward());//RB
    joystick.whenPressed(8, new Climb());//Start button
    
  }
}
