/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class ElevateTo_NEW extends Command {

  private boolean doHold = true;
  private double targetPos = 0;
  private double actualTarget = 0;

  //constructers
  public ElevateTo_NEW(double pos) {
    requires(Robot.elevator);
    this.doHold = true;
    this.targetPos = pos;
  }

  public ElevateTo_NEW(double pos, boolean hold) {
    requires(Robot.elevator);
    this.doHold = hold;
    this.targetPos = pos;
  } 

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevator.clearI();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.platedispenser.status){
      actualTarget = targetPos+150;
    }else{
      actualTarget = targetPos;
    }
    if (Math.abs(actualTarget - Robot.elevator.getPosition()) > 300){
      Robot.elevator.ProfileToPoosition(this.actualTarget);
      System.out.println("mode: profile");
      SmartDashboard.putNumber("error2",actualTarget - Robot.elevator.getPosition());
    }else{
      //DO PID CONTROL
      double error = (actualTarget) - Robot.elevator.getPosition();
      double output = 0.10 + error * 0.005;
      output = range(output, 0.2, -0.2);
      System.out.println(error);
      Robot.elevator.move(output);
    }
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
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
      return false;
    }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    if (!doHold){
      Robot.elevator.stop();
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
