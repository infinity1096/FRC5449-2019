/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Odometry;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.apache.commons.math3.filter.DefaultMeasurementModel;
import org.apache.commons.math3.filter.DefaultProcessModel;
import org.apache.commons.math3.filter.KalmanFilter;
import org.apache.commons.math3.filter.MeasurementModel;
import org.apache.commons.math3.filter.ProcessModel;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import edu.wpi.first.wpilibj.Talon;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Odometry implements Runnable{
    
    private RobotStatus last = new RobotStatus();
    private double[] state;

    public Odometry(){

    }

    @Override
    public void run(){
        
    }

    public double[] get() {
        return this.state.clone();
    }

    private double[] getMeasurement() {
        /*
        double xdd,ydd,h,hd;
        h = Math.toRadians(Robot.gyro.getAngle());
        hd = Math.toRadians(Robot.gyro.getRate());
        xdd = Robot.gyro.getWorldLinearAccelX();
        ydd = Robot.gyro.getWorldLinearAccelY();
        return new double[] {h,hd,xdd,ydd};
        */
        return null;
    }
}
