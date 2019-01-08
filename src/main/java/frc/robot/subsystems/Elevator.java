/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private TalonSRX l1 = new TalonSRX(RobotMap.L1_ELEVATOR_PORT);
  private TalonSRX l2 = new TalonSRX(RobotMap.L2_ELEVATOR_PORT);
  private TalonSRX r1 = new TalonSRX(RobotMap.R1_ELEVATOR_PORT);
  private TalonSRX r2 = new TalonSRX(RobotMap.R2_ELEVATOR_PORT);
  public Elevator(){
    l1.setSensorPhase(false);
    l2.follow(l1);
    r1.follow(l1);
    r2.follow(l1);
    r1.setInverted(true);
    r2.setInverted(true);

  }
  public double[] get(){
    double[] results={0,0};
    results[0]=l1.getSelectedSensorPosition();
    results[1]=l1.getSelectedSensorVelocity();
    return results;
  }
  public void move(double input){
    l1.set(ControlMode.PercentOutput,input*100);
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
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
