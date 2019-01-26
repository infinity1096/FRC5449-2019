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
import frc.robot.commands.Elevator.moveToDown;
import frc.robot.commands.Elevator.moveToMid;
import frc.robot.commands.Elevator.moveToUp;
import frc.robot.commands.Intake_Holder.Shoot;
import frc.robot.commands.PlateDispenser.Extend;
import frc.robot.commands.PlateDispenser.PlacePlate;
import frc.robot.commands.PlateDispenser.Release;
import frc.robot.commands.PlateDispenser.Retract;

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
  public JoystickButton button1 = new JoystickButton(stick0, 11);
  public JoystickButton button2 = new JoystickButton(stick0, 9);
  public JoystickButton button3 = new JoystickButton(stick0, 7);
  

  OI(){
    //button1.whenPressed(new PlacePlate());
    button1.whenPressed(new moveToDown());
    button2.whenPressed(new moveToMid());
    button3.whenPressed(new moveToUp());

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
