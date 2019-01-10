/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DefaultDrive extends Command {
  public DefaultDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /*
    double inputx = Robot.chassis.deathZone(Robot.oi.stick0.getRawAxis(0),0.1);
    double inputy = Robot.chassis.deathZone(Robot.oi.stick0.getRawAxis(1),0.1);
    if (-inputy > 0.8){
    Robot.chassis.arcadeDrive_Speed(0.8, 0,160);
    }else if (-inputy < -0.8){
      Robot.chassis.arcadeDrive_Speed(-0.8, 0,160);
    }else{
      Robot.chassis.arcadeDrive_Speed(0.8*-inputy, inputx*0.3,160);
    }
    */
  }


  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
