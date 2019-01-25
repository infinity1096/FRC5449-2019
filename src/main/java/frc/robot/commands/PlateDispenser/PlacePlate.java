/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.PlateDispenser;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PlacePlate extends Command {

  Timer timer = new Timer();
  public PlacePlate() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.platedispenser);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.timer.reset();
    this.timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (timer.get() < 0.15){
      Robot.platedispenser.extend();
    } else if(timer.get() < 0.20){
      Robot.platedispenser.release();
    } else if(timer.get() < 0.25){
      Robot.platedispenser.retract();
    } else{
      
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return timer.get() > 0.35;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.platedispenser.hold();
    Robot.platedispenser.retract();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
