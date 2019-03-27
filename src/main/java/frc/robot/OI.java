/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import recording.RecordedJoystick;
import frc.robot.commands.DriveTrain.*;
import frc.robot.commands.Group.*;
import frc.robot.commands.Lift.*;
import frc.robot.commands.Misc.*;

public class OI {
  public static RecordedJoystick joystick;
  public static RecordedJoystick buttonBox;
  public OI() {

    //MAIN BUTTON LAYOUT - DO NOT CHANGE - TESTING LAYOUT BELOW
    joystick = new RecordedJoystick(0);
    buttonBox = new RecordedJoystick(1);
    //joystick.whenPressed(1, new WallGrab());//A
    joystick.whenPressed(1, new ToggleBud());//A
    joystick.whenPressed(2, new LiftToLow());//B
    joystick.whenPressed(3, new CaptainKirkPickUp());//X
    joystick.toggleWhenPressed(4, new ToggleStem());//Y
    joystick.whileHeld(5, new SetLift(-.6));//LB - should b down
    joystick.whileHeld(6, new SetLift(.6));//RB - should b up

    //buttonBox.whenPressed(1, new LiftDeliver("low"));
    //buttonBox.whenPressed(2, new LiftDeliver("mid"));
    //buttonBox.whenPressed(3, new LiftDeliver("high"));
    //buttonBox.whenPressed(4, new Climb());
    buttonBox.whenPressed(5, new InterruptAll());
    //buttonBox.whenPressed(6, new AlignFromIr());//Just in case

  }
}
