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
import frc.robot.commands.Common.WaitSlope;
import frc.robot.commands.Elevator.LockClimber;
import frc.robot.commands.Elevator.ElevateTo;
import frc.robot.commands.Elevator.ElevateTo_NEW;
import frc.robot.commands.Elevator.ReleaseClimber;
import frc.robot.commands.Elevator.UnlockClimber;
import frc.robot.commands.Elevator.moveToDown;
import frc.robot.commands.Pusher.PusherIn;
import frc.robot.commands.Pusher.PusherOut;

public class ClimbLow extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ClimbLow() {
    addSequential(new ElevateTo_NEW(1100));
    addSequential(new ReleaseClimber());
    addParallel(new Drive(-0.3));
    addSequential(new moveToDown());
    addSequential(new PusherOut());
    addSequential(new WaitHorizontal());
    addParallel(new ElevateTo(700));
    addSequential(new Delay(0.2));
    addSequential(new UnlockClimber());
    addSequential(new Delay(0.3));
    addParallel(new Drive(0,true));
  }
}
