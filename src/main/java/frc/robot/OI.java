/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import recording.RecordAuto;
import recording.RecordedJoystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.CaptainKirk.CaptainKirkDown;
import frc.robot.commands.CaptainKirk.CaptainKirkUp;
import frc.robot.commands.DriveTrain.*;
import frc.robot.commands.Group.*;
import frc.robot.commands.Lift.*;
import frc.robot.commands.Misc.*;

public class OI {
  public static RecordedJoystick joystick;
  public static RecordedJoystick buttonBox;
  public static JoystickButton rb;
  public static RecordAuto rc;

  public OI() {

    //MAIN BUTTON LAYOUT - DO NOT CHANGE - TESTING LAYOUT BELOW
    joystick = new RecordedJoystick(0);
    buttonBox = new RecordedJoystick(1);
    //joystick.whenPressed(1, new WallGrab());//A
    joystick.whenPressed(1, new ToggleBud());//A
    joystick.whenPressed(2, new LiftToLow());//B
    joystick.whenPressed(3, new CaptainKirkPickUp());//X
    joystick.whenPressed(4, new ToggleStem());//Y
    joystick.whileHeld(5, new SetLift(-.6));//LB - should b down
    joystick.whileHeld(6, new SetLift(.6));//RB - should b up

    joystick.whenPressed(9, new SwitchCam());

    rb = new JoystickButton(joystick.getJoystick(), 8);
    rc = new RecordAuto(joystick, Robot.to_record);
    rb.toggleWhenPressed(rc);

    //buttonBox.whenPressed(1, new LiftDeliver("low"));
    //buttonBox.whenPressed(2, new LiftDeliver("mid"));
    //buttonBox.whenPressed(3, new LiftDeliver("high"));
    //buttonBox.whenPressed(4, new Climb());
    buttonBox.whenPressed(3, new SetRise(true, true, 0));
    buttonBox.whenPressed(6, new SetRise(false, true, 0));
    buttonBox.whenPressed(9, new SetRise(false, false, 0));
    buttonBox.whenPressed(4, new CaptainKirkDown());
    buttonBox.whenPressed(7, new CaptainKirkUp());
    buttonBox.whenPressed(10, new InterruptAll());
    //buttonBox.whenPressed(6, new AlignFromIr());//Just in case

  }

  public boolean isSaving() {
    return rc.isSaving();
  }
}
