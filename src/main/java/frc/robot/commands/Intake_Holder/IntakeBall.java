/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake_Holder;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class IntakeBall extends Command {
  Timer timer = new Timer();
  double accum = 0;
  public IntakeBall() {
    requires(Robot.holder);
    requires(Robot.intake);
    accum = 0;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.reset();
    timer.start();
    accum = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.holder.inTake();
    Robot.intake.takeIn();
    accum += 0.02 * Math.max(Robot.holder.getShooterCurrent()-RobotMap.HOLDER_INTAKE_AMP_THRESHOLD,0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return  timer.get() > 5 || accum > RobotMap.HOLDER_INTAKE_ACCUM_THRESHOLD ;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.intake.stopIntake();
    if (!(timer.get() > 5)){
    Robot.holder.hold();
    }else{
      Robot.holder.stop();
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
