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

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private TalonSRX l1 = new TalonSRX(RobotMap.ELEVATOR_L1_MOTOR_PORT);
  private TalonSRX l2 = new TalonSRX(RobotMap.ELEVATOR_L2_MOTOR_PORT);
  private TalonSRX r1 = new TalonSRX(RobotMap.ELEVATOR_R1_MOTOR_PORT);
  private TalonSRX r2 = new TalonSRX(RobotMap.ELEVATOR_R2_MOTOR_PORT);

  private Solenoid holder = new Solenoid(RobotMap.ELEVATOR_DISPESER_PORT);

  public Elevator(){
    r2.setSensorPhase(true);
    r1.follow(r2);
    l1.follow(r2);
    l2.follow(r2);
    l1.setInverted(true);
    l2.setInverted(false);
    r1.setInverted(false);
    r2.setInverted(true);

    //set feedback sensor
    r2.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    r2.setSelectedSensorPosition(0);
  }

  public void clearEncoder(){
    r2.setSelectedSensorPosition(0);
  }

  public void Release(){
    this.holder.set(false);
  }

  public void Retract(){
    this.holder.set(true);
  }

  public void move(double input){
    r2.set(ControlMode.PercentOutput,input);
  }
  /*
  public double[] get(){
    double[] results={0,0};
    results[0]=l1.getSelectedSensorPosition();
    results[1]=l1.getSelectedSensorVelocity();
    return results;
  }

  public void movetoposition(double input){
    l1.selectProfileSlot(1, 0);
    l1.set(ControlMode.Position,input);
  }
  public double[] getoutput(){
    double[] results={0,0};
    results[0]=l1.getOutputCurrent()+l2.getOutputCurrent();
    results[1]=r1.getOutputCurrent()+r2.getOutputCurrent();
    return results;
  }
  */
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
