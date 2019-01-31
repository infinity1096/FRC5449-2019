/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class PosDrive extends Command {

  private double[] target = { 0, 0, 0 };
  private double[] RobotPos = { 0, 0, 0 };
  private double kp = RobotMap.CHASSIS_POSDRIVE_KP;
  private double ka = RobotMap.CHASSIS_POSDRIVE_KA;
  private double kb = RobotMap.CHASSIS_POSDRIVE_KB;

  public PosDrive(double targetx, double targety, double targettheta) {
    this.target = new double[] { targetx, targety, targettheta};
    requires(Robot.chassis);
  }

  public PosDrive(double[] target) {
    this.target = target.clone();
    requires(Robot.chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.RobotPos[0] = Robot.odometry.get()[0];
    this.RobotPos[1] = Robot.odometry.get()[1];
    double heading;
    heading = Math.toRadians(Robot.gyro.getYaw()) + Math.PI / 2;
    heading = Math.atan2(Math.sin(heading), Math.cos(heading));
    this.RobotPos[2] = heading;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    this.RobotPos[0] = Robot.odometry.get()[0];
    this.RobotPos[1] = Robot.odometry.get()[1];
    double heading;
    heading = Math.toRadians(-Robot.gyro.getYaw()) + Math.PI / 2;
    heading = Math.atan2(Math.sin(heading), Math.cos(heading));
    this.RobotPos[2] = heading;
    double p = Math.hypot(target[0] - RobotPos[0], target[1] - RobotPos[1]);

    double aph;
    double beta;
    double p_output;
    double omega_output;
    double[] u = {0,0};


      aph = Math.atan2((target[1] - RobotPos[1]), (target[0] - RobotPos[0])) - (RobotPos[2]);
      aph = Math.atan2(Math.sin(aph), Math.cos(aph));
      if (-Math.PI / 2 < aph && aph <= Math.PI / 2) {
        aph = Math.atan2((target[1] - RobotPos[1]), (target[0] - RobotPos[0])) - (RobotPos[2]);
        aph = Math.atan2(Math.sin(aph), Math.cos(aph));// restrain angle to [-pi,pi]
        beta = -RobotPos[2] - aph + target[2];
        beta = Math.atan2(Math.sin(beta), Math.cos(beta));
        // compute control outputs
        p_output = kp * p;
        omega_output = ka * aph + kb * beta;
        // do simulation - Xd = Bu ~ Xt = Xt-1 + Xd * dt
        u = new double[] { p_output, omega_output };
      } else {// in case the goal is behind the robot
        double Xv_ = RobotPos[2] + Math.PI;
        Xv_ = Math.atan2(Math.sin(Xv_), Math.cos(Xv_));
        aph = Math.atan2((target[1] - RobotPos[1]), (target[0] - RobotPos[0])) - (Xv_);
        aph = Math.atan2(Math.sin(aph), Math.cos(aph));// restrain angle to [-pi,pi]
        beta = -RobotPos[2] - aph + (target[2]);
        beta = Math.atan2(Math.sin(beta), Math.cos(beta));
        // compute control outputs
        p_output = kp * p;
        omega_output = ka * aph + kb * beta;
        // do simulation - Xd = Bu ~ Xt = Xt-1 + Xd * dt
        u = new double[] { -p_output, omega_output };
      }
      SmartDashboard.putNumber("u0", u[0]);
      SmartDashboard.putNumber("u1", u[1]);
      Robot.chassis.arcadeDrive(u);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    this.RobotPos[0] = Robot.odometry.get()[0];
    this.RobotPos[1] = Robot.odometry.get()[1];
    double heading;
    heading = Math.toRadians(-Robot.gyro.getYaw()) + Math.PI / 2;
    heading = Math.atan2(Math.sin(heading), Math.cos(heading));
    this.RobotPos[2] = heading;
    double p = Math.hypot(target[0] - RobotPos[0], target[1] - RobotPos[1]);
    return (Math.abs(p) < 0.05
    && Math.abs(Math.atan2(Math.sin(RobotPos[2] - target[2]), Math.cos(RobotPos[2] - target[2]))) < 0.05);
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
