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
  //motors
    //testing
    public static final int TESTING_MOTOR_PORT = 1;
    //chassis
    public static final int LF_MOTOR_PORT = 1;
    public static final int LM_MOTOR_PORT = 2;
    public static final int LR_MOTOR_PORT = 3;
    public static final int RF_MOTOR_PORT = 8;
    public static final int RM_MOTOR_PORT = 9;
    public static final int RR_MOTOR_PORT = 10;

    public static final int L1_ELEVATOR_PORT = 7;
    public static final int L2_ELEVATOR_PORT = 8;
    public static final int R1_ELEVATOR_PORT = 9;
    public static final int R2_ELEVATOR_PORT = 10;

    //intake
    public static final int INTAKE_MOTOR = 11;
    public static final int powerIn = 1;//this value remains to be determined
    public static final int powerOut = -1;//this value remains to be determined
}
