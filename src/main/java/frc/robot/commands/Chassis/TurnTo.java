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

public class TurnTo extends Command {

  //TODO Parameters
	private double Kp = RobotMap.CHASSIS_TURNING_P;
	private double Kd = RobotMap.CHASSIS_TURNING_D;
	private double allowedError = 0.017;
	private Timer timer;
  private double error = 0;
  private double errordot = 0;
	private double lastTime;
	private double angleTarget;
	private double lastoutput;
	double timeout = 3;
	
    public TurnTo(double TargetRADIUS) {
        requires(Robot.chassis);
        this.angleTarget = TargetRADIUS;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
      timer = new Timer();
    	timer.reset();
    	timer.start();
      lastTime = -0.02;   
      double heading = (Math.toRadians(-Robot.gyro.getYaw()) + Math.PI/2);
      heading = Math.atan2(Math.sin(heading),Math.cos(heading));
      error = angleTarget - heading;
      error = Math.atan2(Math.sin(error),Math.cos(error));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      //Get Error and Error dot
      double heading = (Math.toRadians(-Robot.gyro.getYaw()) + Math.PI/2);
      heading = Math.atan2(Math.sin(heading),Math.cos(heading));
    	error = angleTarget - heading;
      error = Math.atan2(Math.sin(error),Math.cos(error));
      errordot = Math.toRadians(Robot.gyro.getRate());
      errordot = -Math.signum(error) * Math.abs(errordot);
      System.out.println(errordot);
      //Compute control input
    	double P_output = Kp*error;
    	double D_output = range(Kd*errordot,-0.2,0.2);
      double output = P_output + D_output;
        //avoid deadzones, don't want to use I at all
        output = range2(output,0.06,0.4);
      Robot.chassis.arcadeDrive(0, output);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

        return (Math.abs(error)<allowedError && Math.abs(errordot) < 0.01) || (timer.get() > 3);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.arcadeDrive(0, 0);
    }

    private double range2(double val,double min,double max){
    	max = Math.abs(max);
    	min = Math.abs(min);
    	if (Math.abs(val)<min){
    		return Math.signum(val) * min;
    	} else if (Math.abs(val) > max){
    		return Math.signum(val) * max;
    	}else{
    		return Math.signum(val) * Math.abs(val);
    	}
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//TODO
    }
	 private double range(double val,double min,double max){
	    	if (val < min){
	    		return min;
	    	}else if (val > max){
	    		return max;
	    	}else{
	    		return val;
	    	}
	  } 
}
