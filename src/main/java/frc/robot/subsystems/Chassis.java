/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.awt.Point;

import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.apache.commons.math3.filter.KalmanFilter;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DefaultDrive;

/**
 * Add your docs here.
 */
public class Chassis extends Subsystem {

  public TalonSRX testmotor = new TalonSRX(RobotMap.TESTING_MOTOR_PORT);
  /*
  private TalonSRX lf = new TalonSRX(RobotMap.LF_MOTOR_PORT);
  private TalonSRX lm = new TalonSRX(RobotMap.LM_MOTOR_PORT);
  private TalonSRX lr = new TalonSRX(RobotMap.LR_MOTOR_PORT);
  private TalonSRX rf = new TalonSRX(RobotMap.RF_MOTOR_PORT);
  private TalonSRX rm = new TalonSRX(RobotMap.RM_MOTOR_PORT);
  private TalonSRX rr = new TalonSRX(RobotMap.RR_MOTOR_PORT);
  */
  public Chassis(){
    testmotor.setSensorPhase(false);
    testmotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,30);
    //testmotor.setSelectedSensorPosition(0, 0, 10);
    /*
    lm.follow(lf);
    lr.follow(lf);
    rm.follow(rf);
    rr.follow(rf);
    */
  }

  public static TrajectoryPoint[] convertTrajectoryPoint(double[][] profile, int totalCount){
    TrajectoryPoint point = new TrajectoryPoint();
    TrajectoryPoint[] points = new TrajectoryPoint[totalCount];
    for (int i = 0; i < totalCount; ++i){
      double K_sensor = 13;
      point.position = profile[i][0] * K_sensor;//Rotations -> units
      point.velocity = profile[i][1] / 600.0f * K_sensor;//RPM -> units/100ms
      point.timeDur = (int)profile[i][2];
      point.profileSlotSelect0 = 2;
      point.useAuxPID  = false;
      point.zeroPos = false;
      if (i==0){
        point.zeroPos = true;
      }
  
      point.isLastPoint = false;
      if (i+1==totalCount){
        point.isLastPoint = true;
      } 
      //save it to the long array.
      points[i] = point;
    }
    return points;
  }




  /*
  public void tankDrive(double lt_input, double rt_input){
      lf.set(ControlMode.PercentOutput, lt_input);
      rf.set(ControlMode.PercentOutput, rt_input);
     
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

  public void arcadeDrive(double Power, double Rotate){//this is used for giving power for straight running and rotating 
      double leftPower, rightPower;
      leftPower = range(Power +  Rotate, 1, -1);
      rightPower = range(Power - Rotate, 1, -1);
      lf.set(ControlMode.PercentOutput, leftPower);
      rf.set(ControlMode.PercentOutput, rightPower);
  }
  
public double[][] getEncoderValue(){
    double encoderPosition[] = {lf.getSelectedSensorPosition(), rf.getSelectedSensorPosition()};
    double encoderVelocity[] = {lf.getSelectedSensorVelocity(), rf.getSelectedSensorVelocity()};
    double encoderValue[][] = {encoderVelocity, encoderPosition};

    return encoderValue;
}

public double[] getCurrent(){
  double[] results = {0,0};
  results[0] = lf.getOutputCurrent() + lm.getOutputCurrent() + lr.getOutputCurrent();
  results[1] = rf.getOutputCurrent() + rm.getOutputCurrent() + rr.getOutputCurrent();
  return results;

}
*/

public double deathZone(double value, double zone){
    if(Math.abs(value) > zone ){
      return value;
    }
    else{
      return 0;
    }
}


  public void drive(double input){
    //testmotor.set(ControlMode.PercentOutput, input);
    //testmotor.set(ControlMode.Velocity, input * 300);
  }

  public double getP(){
    return testmotor.getSelectedSensorPosition();
  }
  public double getV(){
    return testmotor.getSelectedSensorVelocity();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DefaultDrive());
  }
}
