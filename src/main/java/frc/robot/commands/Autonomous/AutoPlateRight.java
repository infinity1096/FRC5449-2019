/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.Chassis.Drive;
import frc.robot.commands.Chassis.PosDrive;
import frc.robot.commands.Chassis.TurnTo;
import frc.robot.commands.Common.Delay;
import frc.robot.commands.Elevator.ElevateTo;
import frc.robot.commands.Elevator.ElevateTo_NEW;
import frc.robot.commands.Elevator.moveToDown;
import frc.robot.commands.PlateDispenser.PlacePlate;
import frc.robot.commands.PlateDispenser.initPlate;

public class AutoPlateRight extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoPlateRight() {
    addSequential(new AutonomousInit());
    addParallel(new ElevateTo_NEW(RobotMap.ELEVATOR_DOWN_POS));
    addParallel(new Drive(-0.2));
    addSequential(new Delay(2));
    addSequential(new PosDrive(1.8809,-2.7101,Math.PI*2/3));
    addSequential(new TurnTo(Math.PI*2/3));
  }
}
