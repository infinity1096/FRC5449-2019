/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Odometry;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.MatrixUtils;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class UpdateOdometryPos extends Command {
  double x,y,h;
  double deltax,deltaz;
  boolean isValid = false;

  public UpdateOdometryPos() {  
    }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
        //get data from smartdashboard, if "is valid" is true
        isValid = SmartDashboard.getNumber("isValid",0)==1;
        if (isValid){
          x = SmartDashboard.getNumber("tvec-x",0);
          y = SmartDashboard.getNumber("tvec-y",0);
          h = SmartDashboard.getNumber("tvec-h",0);
          deltax = SmartDashboard.getNumber("tvecs0",0);
          deltaz = SmartDashboard.getNumber("tvecs2",0);

          h = h + Math.PI - Math.PI/2;
          double[][] transf_d = {{Math.cos(h),Math.sin(h)},{-Math.sin(h),Math.cos(h)}};
          RealMatrix transf = MatrixUtils.createRealMatrix(transf_d);
          RealVector delta = new ArrayRealVector(new double[] {deltax,deltaz});
          RealVector delta_U = transf.preMultiply(delta);
          System.out.println(delta_U);
          Robot.odometry.setPos(delta_U.toArray());
          Robot.chassis.isVision = true;
        }else{
         //do nothing 
          Robot.chassis.isVision = false;
        }

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
