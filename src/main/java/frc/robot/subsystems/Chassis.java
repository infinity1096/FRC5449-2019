/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

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

  private TalonSRX testmotor = new TalonSRX(RobotMap.TESTING_MOTOR_PORT);
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


  

  //measurments
  /*
  public double[] getCurrent(){
    double[] results = {0,0};
    results[0] = lf.getOutputCurrent() + lm.getOutputCurrent() + lr.getOutputCurrent();
    results[1] = rf.getOutputCurrent() + rm.getOutputCurrent() + rr.getOutputCurrent();
    return results;
  }
  
  public double[][] getState(){
    double [][] results = {{0,0},{0,0}};
    //results [0,:] = Position, [1,:] = velocity.
    //TODO
  }
  */

  

  public void drive(double input){
    //testmotor.set(ControlMode.PercentOutput, input);
    testmotor.set(ControlMode.Velocity, input * 200);
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
