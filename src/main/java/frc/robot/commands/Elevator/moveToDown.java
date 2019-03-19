/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class moveToDown extends Command {



  //constructers
  public moveToDown() {
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevator.clearI();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.platedispenser.status){
      Robot.elevator.ProfileToPoosition(RobotMap.ELEVATOR_DOWN_POS + 150);
    }else{
      Robot.elevator.ProfileToPoosition(RobotMap.ELEVATOR_DOWN_POS);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (!Robot.platedispenser.status){
      return Math.abs(Robot.elevator.getPosition() - RobotMap.ELEVATOR_DOWN_POS) <= RobotMap.ELEVATOR_AllOWABLE_ERROR;
    }else{
      return Math.abs(Robot.elevator.getPosition() - (RobotMap.ELEVATOR_DOWN_POS+150)) <= RobotMap.ELEVATOR_AllOWABLE_ERROR;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

      //Robot.elevator.stop();

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

}
