/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ElevateTo extends Command {

  private boolean doHold = true;
  private double targetPos = 0;

  //constructers
  public ElevateTo(double pos) {
    requires(Robot.elevator);
    this.doHold = true;
    this.targetPos = pos;
  }

  public ElevateTo(double pos,boolean hold) {
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
    Robot.elevator.ProfileToPoosition(this.targetPos);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.elevator.getPosition() - this.targetPos) <= RobotMap.ELEVATOR_AllOWABLE_ERROR;
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
