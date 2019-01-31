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
    r2.configSelectedFeedbackCoefficient(RobotMap.ELEVATOR_ENCODERUNIT_TO_MILLIMETERS_COEFF);
  }


  //climber supporter holder
  public void Release(){
    this.holder.set(true);
  }

  public void Retract(){
    this.holder.set(false);
  }
  //basic move
  public void move(double input){
    r2.set(ControlMode.PercentOutput,input);
  }

  public void stop(){
    r2.set(ControlMode.PercentOutput,0);
  }
  
  //Advanced move
  public void movetoposition(double input){
    input = input - RobotMap.ELEVATOR_MILIMETERS_OFFSET;
    r2.selectProfileSlot(1, 0);
    r2.set(ControlMode.Position,input);

  }

  public void ProfileToPoosition(double position){
    position = position - RobotMap.ELEVATOR_MILIMETERS_OFFSET;
    r2.selectProfileSlot(2, 0);
    r2.set(ControlMode.MotionMagic, position);
  }


  //get sensor readings
  public double[] getOutput(){
    double[] results={0,0};
    results[0]=l1.getOutputCurrent()+l2.getOutputCurrent();
    results[1]=r1.getOutputCurrent()+r2.getOutputCurrent();
    return results;
  }

  public double getPosition(){
    double results=0;
    results=r2.getSelectedSensorPosition();
    results = results + RobotMap.ELEVATOR_MILIMETERS_OFFSET;
    return results;
  }

  //reset commands
  public void clearEncoder(){
    r2.setSelectedSensorPosition(0);
  }

  public void clearI(){
    r2.setIntegralAccumulator(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
