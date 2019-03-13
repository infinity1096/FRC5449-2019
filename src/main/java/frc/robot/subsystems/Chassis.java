/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motion.TrajectoryPoint;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Chassis.DefaultDrive;

public class Chassis extends Subsystem {

  private CANSparkMax lf = new CANSparkMax(RobotMap.CHASSIS_LF_MOTOR_PORT,MotorType.kBrushless);
  private CANSparkMax lm = new CANSparkMax(RobotMap.CHASSIS_LM_MOTOR_PORT,MotorType.kBrushless);
  private CANSparkMax lr = new CANSparkMax(RobotMap.CHASSIS_LR_MOTOR_PORT,MotorType.kBrushless);
  private CANSparkMax rf = new CANSparkMax(RobotMap.CHASSIS_RF_MOTOR_PORT,MotorType.kBrushless);
  private CANSparkMax rm = new CANSparkMax(RobotMap.CHASSIS_RM_MOTOR_PORT,MotorType.kBrushless);
  private CANSparkMax rr = new CANSparkMax(RobotMap.CHASSIS_RR_MOTOR_PORT,MotorType.kBrushless);
  
  private Encoder encoderl  = new Encoder(RobotMap.CHASSIS_ENCODERL_PORT_A, RobotMap.CHASSIS_ENCODERL_PORT_B);
  private Encoder encoderr  = new Encoder(RobotMap.CHASSIS_ENCODERR_PORT_A, RobotMap.CHASSIS_ENCODERR_PORT_B);

  public Chassis(){
    

    lm.follow(lf);
    lr.follow(lf);
    rm.follow(rf);
    rr.follow(rf);
    lf.setInverted(true);
    lm.setInverted(true);
    lr.setInverted(true);
    rf.setInverted(false);
    rm.setInverted(false);
    rr.setInverted(false);
    lf.setIdleMode(IdleMode.kBrake);
    lm.setIdleMode(IdleMode.kBrake);
    lr.setIdleMode(IdleMode.kBrake);
    rf.setIdleMode(IdleMode.kBrake);
    rm.setIdleMode(IdleMode.kBrake);
    rr.setIdleMode(IdleMode.kBrake);
    

    encoderl.setReverseDirection(true);
    encoderr.setReverseDirection(false);
    encoderl.reset();
    encoderr.reset();
  }
  
  public static TrajectoryPoint[] convertTrajectoryPoint(double[][] profile, int totalCount){
    TrajectoryPoint[] points = new TrajectoryPoint[totalCount];
    for (int i = 0; i < totalCount; ++i){
      TrajectoryPoint point = new TrajectoryPoint();
      double K_sensor = 130;
      point.position = profile[i][0] * K_sensor;//Rotations -> units
      point.velocity = profile[i][1] / 600 * K_sensor;//RPM -> units/100ms
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
      point = null;
    }
    return points;
  }
/*
  public void tankDrive(double lt_input, double rt_input){
      lf.set(ControlMode.PercentOutput, lt_input);
      rf.set(ControlMode.PercentOutput, rt_input);
  }
*/
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
      leftPower = range(Power -  Rotate, 1, -1);
      rightPower = range(Power + Rotate, 1, -1);
      lf.set(leftPower);
      rf.set(rightPower);
  }

  public void arcadeDrive(double[] u){//this is used for giving power for straight running and rotating 
    arcadeDrive(u[0],u[1]);
  }

/*
  public void arcadeDrive_Speed(double Power, double Rotate,double Kspeed){//this is used for giving power for straight running and rotating 
    double leftPower, rightPower;
    Power = Kspeed * Power;
    Rotate = Kspeed * Rotate;
    leftPower = range(Power +  Rotate, Kspeed, -Kspeed);
    rightPower = range(Power - Rotate, Kspeed, -Kspeed);
    lf.selectProfileSlot(1, 0);
    rf.selectProfileSlot(1, 0);
    lf.set(ControlMode.Velocity, leftPower);
    rf.set(ControlMode.Velocity, rightPower);
}

public void update(){

}
  */
public double[][] getEncoderValue(){
    double coeff = RobotMap.CHASSIS_ENCUNIT2METERS_COEFF;
    double encoderPosition[] = {encoderl.getDistance() * coeff, encoderr.getDistance() * coeff};
    double encoderVelocity[] = {encoderl.getRate() * coeff, encoderr.getRate() * coeff};
    double encoderValue[][] = {encoderPosition, encoderVelocity};

    return encoderValue;
}

public double[] getCurrent(){
  double[] results = {0,0};
  results[0] = lf.getOutputCurrent() + lm.getOutputCurrent() + lr.getOutputCurrent();
  results[1] = rf.getOutputCurrent() + rm.getOutputCurrent() + rr.getOutputCurrent();
  return results;
}

/*
public double[][] getEncoderValue(){
  double encoderPosition[] = {testmotor.getSelectedSensorPosition()};
  double encoderVelocity[] = {testmotor.getSelectedSensorPosition()};
  double encoderValue[][] = {encoderPosition, encoderVelocity};
  return encoderValue;
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

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DefaultDrive());
  }
}
