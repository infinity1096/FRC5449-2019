/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake_Holder;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class HolderToUp extends Command {

  private boolean ishold = true;

  private double accum =0;
  private double comp;
  private double error;

  public HolderToUp() {
    requires(Robot.holder);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    this.accum = 0;

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    comp = 0.13 * Math.cos(Math.toRadians(83 + Robot.holder.getPosition()));
    error = RobotMap.HOLDER_UP_POSITION - Robot.holder.getPosition();
    System.out.println(error);
    double P,I,D;
    P = 5;
    D = 9;//12
    I = 0.003;//0.0014
    SmartDashboard.putNumber("error", error);
    double output = P * error + -D * Robot.holder.getSpeed() + I * this.accum;
    output /= 1023;
    output += comp;
    output = range(output, 0.4, -0.4);
    Robot.holder.move(output);
    if (Math.abs(error) < 10){
      this.accum += 20 * error;
    }
    if (accum > 30000){
      accum = 30000;
    }
    if(accum < -30000){
      accum = -30000;
    } 

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    return  !Robot.holder.is_calibrated();

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

      Robot.holder.stopHolder();

  }
  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
  public double range(double value, double max, double min){
    double finalValue = 0.0;
    if(value > max ){
      finalValue = max;
    }

    else if(value < min){
      finalValue = min;
    }
    else{
      finalValue = value;
    }
   
    return finalValue;
}
}
