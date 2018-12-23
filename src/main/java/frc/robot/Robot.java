/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveTrainDefault;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.GroupTest;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.TestbotDriveTrain;
import frc.robot.subsystems.Zucc;
import recording.RecordedJoystick;
import recording.ReplayAuto;
import frc.robot.subsystems.DriveTrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;

  Command m_autonomousCommand;
  public static SendableChooser<Command> m_chooser = new SendableChooser<>();
  public static SendableChooser<String> to_record = new SendableChooser<>();
  public static RecordedJoystick joystick;
  public static DriveTrain driveTrain;
  public static Zucc zucc;
  public static Lift lift;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */


  private void recordSetUp() {
    to_record.addDefault("R_Test", "/U/test.txt");
    to_record.addObject("R_Left", "/U/left.txt");
    to_record.addObject("R_Right", "/U/right.txt");
    SmartDashboard.putData("Record", to_record);
  }

  private void replaySetUp() {
    m_chooser.addDefault("Test", new ReplayAuto("/U/test.txt", joystick));
    m_chooser.addObject("Left", new ReplayAuto("/U/left.txt", joystick));
    m_chooser.addObject("Right", new ReplayAuto("/U/right.txt", joystick));
    m_chooser.addObject("Group", new GroupTest());
    SmartDashboard.putData("Replay", m_chooser);
  }

  @Override
  public void robotInit() {
    joystick = new RecordedJoystick(0);
    recordSetUp();
    replaySetUp();
    driveTrain = new DriveTrain();
    zucc = new Zucc();
    lift = new Lift();
    SmartDashboard.putBoolean("Recording", joystick.recording);
    SmartDashboard.putString("savepath", "");
    m_oi = new OI();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putBoolean("Recording", joystick.recording);
    SmartDashboard.putBoolean("Replaying", joystick.replay);
    SmartDashboard.putNumber("RecordTime", joystick.getTime());
    SmartDashboard.putBoolean("Saving", m_oi.isSaving());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    // some else for normal autonomous

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
