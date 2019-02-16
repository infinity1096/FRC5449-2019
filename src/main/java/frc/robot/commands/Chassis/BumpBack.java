/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Chassis;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class BumpBack extends Command {
  Timer timer = new Timer();
  double threshold = RobotMap.CHASSIS_BUMP_AMP_THRESHOLD; 
  double accum = 0;

  public BumpBack() {
    requires(Robot.chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.reset();
    timer.start();
    threshold = RobotMap.CHASSIS_BUMP_AMP_THRESHOLD;
    accum = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double current = (Robot.chassis.getCurrent()[0] + Robot.chassis.getCurrent()[1])/2;
    System.out.println(current);
    accum += 0.02 * Math.max(current-RobotMap.CHASSIS_BUMP_AMP_THRESHOLD,0);
    Robot.chassis.arcadeDrive(-0.2, 0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return accum >= RobotMap.CHASSIS_BUMP_ACCUM_THRESHOLD || timer.get() > 4;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.chassis.arcadeDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
