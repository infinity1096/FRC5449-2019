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

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Pusher extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  TalonSRX pusherl;
  TalonSRX pusherr;

  private boolean is_calibrated = false;

  public Pusher(){
    pusherl = new TalonSRX(RobotMap.PUSHER_L_PORT);
    pusherr = new TalonSRX(RobotMap.PUSHER_R_PORT);
    
    pusherl.setInverted(false);
    pusherr.setInverted(true);

    pusherl.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    pusherr.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    pusherl.setSelectedSensorPosition(0);
    pusherr.setSelectedSensorPosition(0);

    pusherl.configSelectedFeedbackCoefficient(RobotMap.PUSHER_ENCODERUNIT_TO_MM_COEFF);
    pusherr.configSelectedFeedbackCoefficient(RobotMap.PUSHER_ENCODERUNIT_TO_MM_COEFF);

    is_calibrated = false;
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public double[] getPosition(){
    double results[] = {0,0};
    results[0] = this.pusherl.getSelectedSensorPosition();
    results[1] = this.pusherr.getSelectedSensorPosition();
    return results;
  }

  public double[] getCurrent(){
    double[]results = {0,0};
    results[0] = this.pusherl.getOutputCurrent();
    results[1] = this.pusherr.getOutputCurrent();
    return results;
  }

  public void moveTo(double Position){
    pusherl.selectProfileSlot(1, 0);
    pusherr.selectProfileSlot(1, 0);

    pusherl.set(ControlMode.Position, Position);
    pusherr.set(ControlMode.Position, Position);
  }

  public void profileTo(double Position){
    pusherl.selectProfileSlot(2, 0);
    pusherr.selectProfileSlot(2, 0);

    pusherl.set(ControlMode.MotionMagic, Position);
    pusherr.set(ControlMode.MotionMagic, Position);
  }

  public void moveOut(){
    this.profileTo(RobotMap.PUSHER_OUT_POSITION);
  }

  public void moveIn(){
    this.profileTo(RobotMap.PUSHER_IN_POSITION);
  }

  public void move(double power){
    pusherl.set(ControlMode.PercentOutput, power);
    pusherr.set(ControlMode.PercentOutput, power);
  }

  public void reset(){
    this.pusherl.setSelectedSensorPosition(0);
    this.pusherr.setSelectedSensorPosition(0);
  }

  public void resetl(){
    this.pusherl.setSelectedSensorPosition(0);
  }

  public void resetr(){
    this.pusherr.setSelectedSensorPosition(0);
  }
    
  public void calibrated(){
    this.is_calibrated = true;
  }

  public void movel(double power){
    pusherl.set(ControlMode.PercentOutput, power);
  }

  public void mover(double power){
    pusherr.set(ControlMode.PercentOutput, power);
  }

}
