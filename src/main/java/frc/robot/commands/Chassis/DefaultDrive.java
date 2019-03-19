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
import frc.robot.subsystems.Arduino.Status;

public class DefaultDrive extends Command {
  public DefaultDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.arduino.set(Status.kREMOTE);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    double inputx = Robot.chassis.deathZone(Robot.oi.stick0.getRawAxis(2),0.1);
    double inputy = Robot.chassis.deathZone(Robot.oi.stick0.getRawAxis(1),0.1);

    if (inputx > 0.1){
      inputx = (inputx - 0.1) * 1/0.9;
    }else if(inputx < -0.1){
      inputx = (inputx + 0.1) * 1/0.9;
    }else{
      inputx = 0;
    }

    inputx = Robot.chassis.range(Math.pow(Math.abs(inputx),3),1,0) * Math.signum(inputx);

    Robot.chassis.arcadeDrive(-0.9*inputy, -inputx*0.2);
    
    double status = SmartDashboard.getNumber("status", 0);

    if (status == 1){
      Robot.arduino.set(Status.kFINDING);
    }else{
      Robot.arduino.set(Status.kREMOTE);
    }
    
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
    Robot.arduino.set(Status.kAUTONOMOUS);
  }
}
