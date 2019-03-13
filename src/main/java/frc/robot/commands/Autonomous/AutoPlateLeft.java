/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.Chassis.PosDrive;
import frc.robot.commands.PlateDispenser.initPlate;

public class AutoPlateLeft extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoPlateLeft() {
    addSequential(new initPlate());
    addSequential(new PosDrive(-0.057658,-1.9144,Math.PI/2));
    addSequential(new PosDrive(-1.9809,-2.7101,Math.PI/3));
    addSequential(new PosDrive(-2.3489,-3.4386,Math.PI/3));
  }
}
