/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake_Holder;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Shoot2 extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Shoot2() {
    addSequential(new Shoot());
    addSequential(new HolderToDown());
    addSequential(new CalibrateHolder());
  }
}
