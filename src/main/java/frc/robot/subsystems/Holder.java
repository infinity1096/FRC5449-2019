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

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Holder extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private TalonSRX shooter;
  private TalonSRX turner;
  private double Outpower;
  private double Inpower;
  private boolean is_calibrated = false;
  
  public Holder(){
    Outpower = RobotMap.HOLDER_OUT_POWER;
    Inpower = RobotMap.HOLDER_IN_POWER;
    this.shooter = new TalonSRX(RobotMap.HOLDER_SHOOTER_MOTOR_PORT);
    this.turner = new TalonSRX(RobotMap.HOLDER_TURNER_MOTOR_PORT);

    //initialize
    turner.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    turner.setSelectedSensorPosition(0);
    turner.configSelectedFeedbackCoefficient(RobotMap.HOLDER_ENCODERUNIT_TO_DEG_COEFF);
    this.is_calibrated = false;
  }

  public void moveToPos(double pos){
    this.turner.selectProfileSlot(1, 0);
    this.turner.set(ControlMode.Position, pos);
  }

  public void profileMove(double pos){
    this.turner.selectProfileSlot(2, 0);
    this.turner.set(ControlMode.MotionMagic, pos);
  }

  public void move(double power){
    this.turner.set(ControlMode.PercentOutput, power);
  }

  public void stopHolder(){
    this.turner.set(ControlMode.PercentOutput,0);
  }

  //shooter command
  public void shoot(){
    this.shooter.set(ControlMode.PercentOutput, Outpower);
  }

  public void hold(){
    this.shooter.set(ControlMode.PercentOutput, 0.07);
  }

  public void inTake(){
    this.shooter.set(ControlMode.PercentOutput, Inpower);
  }

  public void stop(){
    this.shooter.set(ControlMode.PercentOutput,0);
  }

  public void calibrated(){
    this.is_calibrated = true;
  }


  public boolean is_calibrated(){
    return this.is_calibrated;
  }

  //get command

  public double getHolderCurrent(){
    return this.turner.getOutputCurrent();
  }

  public double getPosition(){
    return this.turner.getSelectedSensorPosition();
  }

  public double getSpeed(){
    return this.turner.getSelectedSensorVelocity();
  }

  public double getShooterCurrent(){
    return this.shooter.getOutputCurrent();
  }

  //reset command
  public void resetEncoder(){
    this.turner.setSelectedSensorPosition(0);
  }

  public void resetEncoder(int val){
    this.turner.setSelectedSensorPosition(val);
  }

  @Override
  public void initDefaultCommand() {
  }
}
