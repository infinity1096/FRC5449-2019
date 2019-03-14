/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.Common.Delay;
import frc.robot.commands.Elevator.ElevateTo;
import frc.robot.commands.Elevator.ElevateTo_NEW;
import frc.robot.commands.Intake_Holder.CalibrateHolder;
import frc.robot.commands.Intake_Holder.HolderToDown;
import frc.robot.commands.Intake_Holder.HolderToMid;
import frc.robot.commands.Intake_Holder.InitHolderIntake;
import frc.robot.commands.PlateDispenser.RetractPH;

public class RemoteInit extends CommandGroup {
  /**
   * Add your docs here.
   */
  public RemoteInit() {
    addSequential(new RetractPH());
    addParallel(new ElevateTo_NEW(RobotMap.ELEVATOR_DOWN_POS));
    addSequential(new Delay(0.25));
    addSequential(new InitHolderIntake());
    addSequential(new CalibrateHolder());
  }
}
