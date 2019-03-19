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

public class PrepClimbHigh extends CommandGroup {
  /**
   * Add your docs here.
   */
  public PrepClimbHigh() {
    addSequential(new ElevateTo(1630));
    addSequential(new ReleaseClimber());
  }
}
