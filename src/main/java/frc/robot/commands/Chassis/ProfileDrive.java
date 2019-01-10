/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Chassis;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Chassis;

public class ProfileDrive extends Command {
  public ProfileDrive(double[][] profile,int Knum) {
    requires(Robot.chassis);
    this.profile = profile;
    this.knum = Knum;
    this.indicator = 0;
  }

  double kf = 2.7;
  double kp = 1.5;
  int indicator = 0;
  int knum = 0;
  double[][] profile;
  double Ksensor = 130;
  double initialpoint = 0;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.indicator = 0;
    initialpoint = Robot.chassis.getEncoderValue()[0][0];
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double Targetpos = profile[indicator][0] * Ksensor + initialpoint;
    double Targetvel = profile[indicator][1] * Ksensor / 600;
    double currentpos = Robot.chassis.getEncoderValue()[0][0];
    double F = 0, P = 0;
    //Feed forward
    F = kf * Targetvel;
    //Feedback
    P = kp * (Targetpos - currentpos);
    double output = range(F + P,1023,-1023)/1023.d;
    Robot.chassis.testmotor.set(ControlMode.PercentOutput,output);
    this.indicator++;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return indicator >= knum;
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

  public static double range(double value, double max, double min){
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
