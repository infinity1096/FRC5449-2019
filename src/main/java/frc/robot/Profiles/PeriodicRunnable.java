/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Profiles;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Talon;

/**
 * Add your docs here.
 */
public class PeriodicRunnable implements Runnable{
    TalonSRX talon;

    public PeriodicRunnable(TalonSRX talon){
        this.talon = talon;
    }

    public void run(){
        this.talon.processMotionProfileBuffer();
    }
}
