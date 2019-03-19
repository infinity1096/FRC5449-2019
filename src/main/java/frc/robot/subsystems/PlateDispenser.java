/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class PlateDispenser extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Solenoid pusher;
  private Solenoid holder;
  private DigitalInput s1;
  private DigitalInput s2;
  public boolean status = false;  

  public PlateDispenser(){
    this.pusher = new Solenoid(RobotMap.PLATEDISPENSER_PUSHER_SOLENOID_PORT);
    this.holder = new Solenoid(RobotMap.PLATEDISPENSER_HOLDER_SOLENOID_PORT);
    this.s1 = new DigitalInput(RobotMap.PLATEDISPENSER_S1_TOUCH_PORT);
    this.s2 = new DigitalInput(RobotMap.PLATEDISPENSER_S2_TOUCH_PORT);
  }

  //Basic commands
  public void extend(){
    this.pusher.set(true);
    this.status = true;
  }

  public void retract(){
    this.pusher.set(false);
    this.status = false;
  }

  public void hold(){
    this.holder.set(false);
  }

  public void release(){
    this.holder.set(true);
  }


  public boolean[] get(){
    boolean[] results = new boolean[2];
    results[0] = this.s1.get();
    results[1] = this.s2.get(); 
    return results;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
