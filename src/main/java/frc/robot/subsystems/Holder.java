/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
  private DigitalInput holdersensor;
  private long UpPos;
  private long DownPos;
  private double Outpower;
  private double Inpower;
  
  public void Holder(){
    UpPos = RobotMap.HOLDER_UP_POSITION;
    DownPos = RobotMap.HOLDER_UP_POSITION;
    Outpower = RobotMap.HOLDER_OUT_POWER;
    Inpower = RobotMap.HOLDER_IN_POWER;
    this.shooter = new TalonSRX(RobotMap.HOLDER_SHOOTER_MOTOR_PORT);
    this.turner = new TalonSRX(RobotMap.HOLDER_TURNER_MOTOR_PORT);
    this.holdersensor = new DigitalInput(RobotMap.HOLDER_HOLDERSENSOR_IR_PORT);
  }

  public void moveToUp(){
    this.turner.selectProfileSlot(1, 0);
    this.turner.set(ControlMode.Position, UpPos);
  }
  
  public void moveToDown(){
    this.turner.selectProfileSlot(1, 0);
    this.turner.set(ControlMode.Position, DownPos);
  }

  public void shoot(){
    this.shooter.set(ControlMode.PercentOutput, Outpower);
  }

  public void inTake(){
    this.shooter.set(ControlMode.PercentOutput, Inpower);
  }

  public boolean get(){
    return this.holdersensor.get();
  }

  @Override
  public void initDefaultCommand() {
  }
}
