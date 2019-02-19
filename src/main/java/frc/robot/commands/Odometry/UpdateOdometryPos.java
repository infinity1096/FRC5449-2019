/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Odometry;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class UpdateOdometryPos extends Command {
  public UpdateOdometryPos() {
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    double OffsetX = 0.653;
    double OffsetY = 0.000;
    double PositionX = -SmartDashboard.getNumber("TargetX", 0) / 1000.0d + 0.23;
    double PositionY = -SmartDashboard.getNumber("TargetY", 0) / 1000.0d + 0.00;
    Robot.odometry.setPos(OffsetX + PositionX,OffsetY + PositionY);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
