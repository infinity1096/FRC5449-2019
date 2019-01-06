/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DefaultDrive;

/**
 * Add your docs here.
 */
public class Chassis extends Subsystem {

  public Chassis(){}

  private TalonSRX testmotor = new TalonSRX(RobotMap.TESTING_MOTOR_PORT);

  public void drive(double input){
    testmotor.set(ControlMode.PercentOutput, input);
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
