/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  //Settings
  public static boolean IS_GYRO_ENABLED = true;

  //motors
    //testing
    public static final int TESTING_MOTOR_PORT = 1;
    //chassis
    public static final int CHASSIS_LF_MOTOR_PORT = 1;
    public static final int CHASSIS_LM_MOTOR_PORT = 2;
    public static final int CHASSIS_LR_MOTOR_PORT = 3;
    public static final int CHASSIS_RF_MOTOR_PORT = 4;
    public static final int CHASSIS_RM_MOTOR_PORT = 5;
    public static final int CHASSIS_RR_MOTOR_PORT = 6;

    public static final int CHASSIS_ENCODERL_PORT_A = 6;
    public static final int CHASSIS_ENCODERL_PORT_B = 7;

    public static final int CHASSIS_ENCODERR_PORT_A = 8;
    public static final int CHASSIS_ENCODERR_PORT_B = 9;
    //Elevator
    public static final int ELEVATOR_L1_MOTOR_PORT = 15;
    public static final int ELEVATOR_L2_MOTOR_PORT = 14;
    public static final int ELEVATOR_R1_MOTOR_PORT = 7;
    public static final int ELEVATOR_R2_MOTOR_PORT = 8;
    //Holder
    public static final int HOLDER_SHOOTER_MOTOR_PORT = 12;
    public static final int HOLDER_TURNER_MOTOR_PORT = 10;
    //intake
    public static final int INTAKE_MOTOR = 11;
    //pusher
    public static final int PUSHER_L_PORT = 13;
    public static final int PUSHER_R_PORT = 9;
//Pneumatics
  //PCM
    public static final int PCM_PORT = 0;
  //Solenoid
    public static final int ELEVATOR_DISPESER_PORT = 0;
    public static final int PLATEDISPENSER_PUSHER_SOLENOID_PORT = 1;
    public static final int PLATEDISPENSER_HOLDER_SOLENOID_PORT = 2;

//Sensors
  //touch sensor
    public static final int PLATEDISPENSER_S1_TOUCH_PORT = 0;
    public static final int PLATEDISPENSER_S2_TOUCH_PORT = 1;
  //IR sensor
    public static final int HOLDER_HOLDERSENSOR_IR_PORT = 2;


//Chassis
  //hardware parameters
  public static final double CHASSIS_ENCUNIT2METERS_COEFF = 3.2867e-4;
  //software parameters
  public static final double CHASSIS_POSDRIVE_KP = 0.1;//KP > 0
  public static final double CHASSIS_POSDRIVE_KA = 0.3;//KA - KP > 0
  public static final double CHASSIS_POSDRIVE_KB = -0.2;//KB < 0
//Elevator
  //hardware parameters
    public static final double ELEVATOR_ENCODERUNIT_TO_MILLIMETERS_COEFF = 0.0493; 
    public static final double ELEVATOR_MILIMETERS_OFFSET = 480;
  //Software Parameters
    public static final double ELEVATOR_DOWN_POS = ELEVATOR_MILIMETERS_OFFSET;
    public static final double ELEVATOR_MID_POS = 1192;//this value remains to be determined
    public static final double ELEVATOR_UP_POS = 1902;//this value remains to be determined
    public static final double ELEVATOR_AllOWABLE_ERROR = 10;//this value remains to be determined

//Holder
  //hardware Parameters
    public static final double HOLDER_ENCODERUNIT_TO_DEG_COEFF = 0.3271;
    public static final int HOLDER_CALIBRATE_OFFSET = -106;
  //Software Parameters
    public static final double HOLDER_OUT_POWER = -1.0;
    public static final double HOLDER_IN_POWER =  0.5;

    public static final long HOLDER_UP_POSITION = 20;
    public static final long HOLDER_MID_POSITION = 0;
    public static final long HOLDER_DOWN_POSITION = -90;
    
    public static final double HOLDER_ALLOWABLE_ERROR = 3;
    public static final double HOLDER_CALIBRATION_AMP_THRESHOLD = 2.0;//Amp
    public static final double HOLDER_CALIBRATION_ACCUM_THRESHOLD = 0.25;//Amp * sec

    public static final double HOLDER_INTAKE_AMP_THRESHOLD = 10; //Amp
    public static final double HOLDER_INTAKE_ACCUM_THRESHOLD = 1;//Amp * sec
//intake
  public static final double powerIn = -0.6;//this value remains to be determined
  public static final double powerOut = 0.5;//this value remains to be determined

  

//pusher
  //hardware parameters
  public static final double PUSHER_ENCODERUNIT_TO_MM_COEFF = 0.4215;//mm
  //software parameters
  public static final double PUSHER_IN_POSITION = 0;
  public static final double PUSHER_OUT_POSITION = 600;
  public static final double PUSHER_ALLOWABLE_ERROR = 5;
  public static final double PUSHER_CALIBRATION_AMP_THRESHOLD = 0.75;//Amp
  public static final double PUSHER_CALIBRATION_ACCUM_THRESHOLD = 0.2;//Amp * sec


}