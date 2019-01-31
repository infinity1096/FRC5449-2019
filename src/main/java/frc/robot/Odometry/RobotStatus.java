/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Odometry;

/**
 * Add your docs here.
 */
public class RobotStatus {
    public double x;//meter
    public double y;//meter
    public double xd;//m/s
    public double yd;//m/s
    public double xdd;//metes/second^2
    public double ydd;//metes/second^2
    public double h;//rad
    public double hd;//rad/s
    public double t;//update time in second

    
    public RobotStatus(){

    }

    public RobotStatus(double x, double y, double xd, double yd, double xdd, double ydd, double h, double hd, double t){
        this.x = x;
        this.y = y;
        this.xd = xd;
        this.yd = yd;
        this.xdd = xdd;
        this.ydd = ydd;
        this.h = h;
        this.hd = hd;
        this.t = t;
    }

    public RobotStatus clone(){
        return new RobotStatus(x,y,xd,yd,xdd,ydd,h,hd,t);
    }
}
