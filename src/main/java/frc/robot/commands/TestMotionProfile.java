/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motion.BufferedTrajectoryPointStream;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Profiles.Profile1;
import frc.robot.subsystems.Chassis;

public class TestMotionProfile extends Command {
  public TestMotionProfile() {
    requires(Robot.chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.chassis.testmotor.selectProfileSlot(2, 0);
    BufferedTrajectoryPointStream stream = new BufferedTrajectoryPointStream();
    stream.Write(Chassis.convertTrajectoryPoint(Profile1.Points,Profile1.kNumPoints));
    Robot.chassis.testmotor.startMotionProfile(stream,10, ControlMode.MotionProfile);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
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