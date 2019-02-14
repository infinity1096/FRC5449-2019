/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake_Holder;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class CalibrateHolder extends Command {
  Timer timer = new Timer();

  public CalibrateHolder() {
    requires(Robot.holder);
    this.accum = 0;
  }
  private double accum = 0;
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.holder.move(-0.2);
    timer.reset();
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    accum += 0.02 * Math.max(Robot.holder.getHolderCurrent()-RobotMap.HOLDER_CALIBRATION_AMP_THRESHOLD,0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return this.accum > RobotMap.HOLDER_CALIBRATION_ACCUM_THRESHOLD;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.holder.stopHolder();
    Robot.holder.resetEncoder(RobotMap.HOLDER_CALIBRATE_OFFSET);
    Robot.holder.calibrated();

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
