/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.Chassis.PosDrive;
import frc.robot.commands.Odometry.UpdateOdometryPos;

public class AutoRetrivePlate extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoRetrivePlate() {
    addSequential(new UpdateOdometryPos());
    addSequential(new PosDrive(0.653, 0, Math.PI/2));
  }
}
