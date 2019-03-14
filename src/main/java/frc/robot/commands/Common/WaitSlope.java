/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Common;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WaitSlope extends Command {

  public WaitSlope() {
    this.allowed_error = 4;
  }
  public WaitSlope(double ALLOWED_DEG) {
    this.allowed_error = ALLOWED_DEG; 
  }
  private double allowed_error = 5; //DEG

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    System.out.println(Robot.gyro.getRoll());
    return Math.abs(Robot.gyro.getRoll()+30)<this.allowed_error;
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
