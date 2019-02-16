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
import frc.robot.commands.Chassis.ProfileDrive;
import frc.robot.commands.Elevator.ReleaseClimber;
import frc.robot.commands.Elevator.moveToDown;
import frc.robot.commands.Elevator.moveToMid;
import frc.robot.commands.Elevator.moveToUp;
import frc.robot.commands.Intake_Holder.HolderToDown;
import frc.robot.commands.Intake_Holder.HolderToMid;
import frc.robot.commands.Intake_Holder.HolderToUp;
import frc.robot.commands.Intake_Holder.IntakeBall;
import frc.robot.commands.Intake_Holder.Shoot;
import frc.robot.commands.PlateDispenser.Extend;
import frc.robot.commands.PlateDispenser.PlacePlate;
import frc.robot.commands.PlateDispenser.Release;
import frc.robot.commands.PlateDispenser.Retract;
import frc.robot.commands.PlateDispenser.RetrievePlate;
import frc.robot.commands.Pusher.PusherIn;
import frc.robot.commands.Pusher.PusherOut;

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


  public JoystickButton place = new JoystickButton(stick0, 1);
  public JoystickButton extend = new JoystickButton(stick0, 5);
  public JoystickButton retrive = new JoystickButton(stick0, 3);
  public JoystickButton ElevatorUp = new JoystickButton(stick0, 7);
  public JoystickButton ElevatorMid = new JoystickButton(stick0, 9);
  public JoystickButton ElevatorDown = new JoystickButton(stick0, 11);
  public JoystickButton HolderUp = new JoystickButton(stick0, 8);
  public JoystickButton HolderMid = new JoystickButton(stick0, 10);
  public JoystickButton HolderDown = new JoystickButton(stick0, 12);
  public JoystickButton intake = new JoystickButton(stick0, 2);
  public JoystickButton shoot = new JoystickButton(stick0, 6);
  public JoystickButton RetrivePlate = new JoystickButton(stick0, 4);

  OI(){

    //test joystick
    ElevatorUp.whenPressed(new moveToUp());
    ElevatorMid.whenPressed(new moveToMid());
    ElevatorDown.whenPressed(new moveToDown());
    HolderUp.whenPressed(new HolderToUp());
    HolderMid.whenPressed(new HolderToMid());
    HolderDown.whenPressed(new HolderToDown());    
    place.whenPressed(new PlacePlate());
    extend.whenPressed(new PusherOut());
    retrive.whenPressed(new PusherIn());
    intake.whenPressed(new IntakeBall());
    RetrivePlate.whenPressed(new RetrievePlate());
    shoot.whenPressed(new Shoot());
    
    //button1.whenPressed(new PlacePlate());    
    //button1.whenPressed(new moveToDown());
    //button2.whenPressed(new moveToMid());
    //button3.whenPressed(new moveToUp());
    
    //intake.whenPressed(new IntakeBall());
    

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
