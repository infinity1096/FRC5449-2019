/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.Elevator.CalibrateElevator;
import frc.robot.commands.Elevator.ElevateTo;
import frc.robot.commands.Elevator.moveToDown;
import frc.robot.commands.Elevator.moveToMid;
import frc.robot.commands.PlateDispenser.initPlate;

public class AutonomousInit extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutonomousInit() {
    addSequential(new CalibrateElevator());
    addSequential(new ElevateTo(RobotMap.ELEVATOR_MID_POS));
    addSequential(new initPlate());
    addSequential(new ElevateTo(RobotMap.ELEVATOR_DOWN_POS));
  }
}
