/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Chassis;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.Common.Delay;
import frc.robot.commands.Common.WaitHorizontal;
import frc.robot.commands.Elevator.LockClimber;
import frc.robot.commands.Elevator.ElevateTo;
import frc.robot.commands.Elevator.ReleaseClimber;
import frc.robot.commands.Elevator.moveToDown;
import frc.robot.commands.Pusher.PusherIn;
import frc.robot.commands.Pusher.PusherOut;

public class Climb extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Climb() {

    /*

    addParallel(new ElevateTo(1650));
    addSequential(new TurnTo(Math.PI/2));
    addSequential(new BumpBack());
    addSequential(new ReleaseClimber());
    */
    addParallel(new Drive(-0.1));
    addSequential(new moveToDown());
    addSequential(new PusherOut());
    addSequential(new WaitHorizontal());
    addParallel(new ElevateTo(700));
    addSequential(new Delay(0.4));
    addParallel(new Drive(0));
    addSequential(new PusherIn());
    

    
    


    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
