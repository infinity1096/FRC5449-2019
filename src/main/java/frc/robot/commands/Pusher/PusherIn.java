/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Pusher;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class PusherIn extends Command {
  public PusherIn() {
    requires(Robot.pusher);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.pusher.moveIn();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs((Robot.pusher.getPosition()[0] + Robot.pusher.getPosition()[1])/2 - RobotMap.PUSHER_IN_POSITION)< RobotMap.PUSHER_ALLOWABLE_ERROR;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.pusher.move(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
