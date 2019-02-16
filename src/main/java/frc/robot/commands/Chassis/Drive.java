/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Drive extends Command {

  private double power = 0.2;
  private boolean stop = false;

  public Drive(double power) {
    requires(Robot.chassis);
    this.power = power;
    this.stop = false;
  }
  
  public Drive(double power,boolean stop) {
    requires(Robot.chassis);
    this.power = power;
    this.stop = stop;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.chassis.arcadeDrive(power, 0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.chassis.arcadeDrive(power, 0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return this.stop;
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
