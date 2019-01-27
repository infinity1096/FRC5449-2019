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

  }

  public void pusherIn(){
    pusher_lt.set(ControlMode.PercentOutput, minimumPower);

  }
  public void pusherOut(){
    pusher_lt.set(ControlMode.PercentOutput, maximumPower);
    
  }
  public double getPusherValue(){
      return 0.1;
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void move(double power){
    pusherl.set(ControlMode.PercentOutput, power);
    pusherr.set(ControlMode.PercentOutput, power);
  }
}
