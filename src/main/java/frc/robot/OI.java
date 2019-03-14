/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Profiles.Profile1;
import frc.robot.commands.Autonomous.RemoteInit;
import frc.robot.commands.Chassis.ClimbHigh;
import frc.robot.commands.Chassis.ClimbLow;
import frc.robot.commands.Chassis.PrepClimbHigh;
import frc.robot.commands.Chassis.ProfileDrive;
import frc.robot.commands.Elevator.ElevateTo;
import frc.robot.commands.Elevator.ElevateTo_NEW;
import frc.robot.commands.Elevator.ReleaseClimber;
import frc.robot.commands.Elevator.moveToDown;
import frc.robot.commands.Elevator.moveToMid;
import frc.robot.commands.Elevator.moveToUp;
import frc.robot.commands.Intake_Holder.HolderToDown;
import frc.robot.commands.Intake_Holder.HolderToMid;
import frc.robot.commands.Intake_Holder.HolderToUp;
import frc.robot.commands.Intake_Holder.IntakeBall;
import frc.robot.commands.Intake_Holder.Shoot;
import frc.robot.commands.Intake_Holder.Shoot2;
import frc.robot.commands.PlateDispenser.Extend;
import frc.robot.commands.PlateDispenser.PlacePlate;
import frc.robot.commands.PlateDispenser.Release;
import frc.robot.commands.PlateDispenser.Retract;
import frc.robot.commands.PlateDispenser.RetrievePlate;
import frc.robot.commands.Pusher.PusherIn;
import frc.robot.commands.Pusher.PusherOut;
import frc.robot.subsystems.Elevator;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.

  public Joystick stick0 = new Joystick(0);
  public Joystick stick1 = new Joystick(1);
  

  public JoystickButton intake = new JoystickButton(stick0, 2);
  public JoystickButton climbLow = new JoystickButton(stick0, 11);
  
  public JoystickButton climbHigh = new JoystickButton(stick0, 7);
  public JoystickButton prepClimbHigh = new JoystickButton(stick0, 9);


  public JoystickButton extend = new JoystickButton(stick1, 5);
  public JoystickButton retrive = new JoystickButton(stick1, 3);
  public JoystickButton ElevatorUp = new JoystickButton(stick1, 7);
  public JoystickButton ElevatorMid = new JoystickButton(stick1, 9);
  public JoystickButton ElevatorDown = new JoystickButton(stick1, 11);
  public JoystickButton RemoteInitialie = new JoystickButton(stick1, 8);
  public JoystickButton HolderMid = new JoystickButton(stick1, 10);
  public JoystickButton HolderDown = new JoystickButton(stick1, 12);
  
  public JoystickButton shoot = new JoystickButton(stick1, 6);
  public JoystickButton cargo = new JoystickButton(stick1, 4);
  public JoystickButton RetrivePlate = new JoystickButton(stick1, 2);
  public JoystickButton place = new JoystickButton(stick1, 1);
  OI(){

    climbHigh.whenPressed(new ClimbHigh());
    climbLow.whenPressed(new ClimbLow());
    prepClimbHigh.whenActive(new PrepClimbHigh());
    
    //test joystick
    ElevatorUp.whenPressed(new ElevateTo_NEW(RobotMap.ELEVATOR_UP_POS));
    ElevatorMid.whenPressed(new ElevateTo_NEW(RobotMap.ELEVATOR_MID_POS));
    ElevatorDown.whenPressed(new ElevateTo_NEW(RobotMap.ELEVATOR_DOWN_POS));

    RemoteInitialie.whenPressed(new RemoteInit());
    HolderMid.whenPressed(new HolderToMid());
    HolderDown.whenPressed(new HolderToDown());    
    place.whenPressed(new PlacePlate());
    extend.whenPressed(new PusherOut());
    retrive.whenPressed(new PusherIn());
    intake.whenPressed(new IntakeBall());
    RetrivePlate.whenPressed(new RetrievePlate());
    shoot.whenPressed(new Shoot2());
    cargo.whenPressed(new ElevateTo(RobotMap.ELEVATOR_CARGO_POS));
  }
  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
