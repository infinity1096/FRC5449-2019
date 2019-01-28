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

public class CalibratePusher extends Command {
  public CalibratePusher() {
    requires(Robot.pusher);
  }

  private double accum_l = 0;
  private double accum_r = 0;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.pusher.move(-0.1);
    this.accum_l = 0;
    this.accum_r = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double[] current = Robot.pusher.getCurrent();
    accum_l += 0.02 * Math.max(current[0] - RobotMap.PUSHER_CALIBRATION_AMP_THRESHOLD,0);
    accum_r += 0.02 * Math.max(current[1] - RobotMap.PUSHER_CALIBRATION_AMP_THRESHOLD,0);
    
    if (accum_l > RobotMap.PUSHER_CALIBRATION_ACCUM_THRESHOLD){
      Robot.pusher.movel(0);
      Robot.pusher.resetl();
    }

    if (accum_r > RobotMap.PUSHER_CALIBRATION_ACCUM_THRESHOLD){
      Robot.pusher.mover(0);
      Robot.pusher.resetr();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (accum_l > RobotMap.PUSHER_CALIBRATION_ACCUM_THRESHOLD) && (accum_r > RobotMap.PUSHER_CALIBRATION_ACCUM_THRESHOLD);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.pusher.move(0);
    Robot.pusher.calibrated();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
