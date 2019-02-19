/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Arduino extends Subsystem {

  private SerialPort serial = new SerialPort(115200, Port.kMXP);

  public enum Status {
    kOFF(0), kDISABLED(1), kREMOTE(2), kAUTONOMOUS(3), kFINDING(4);
    @SuppressWarnings("MemberName")
    public final int value;
    Status(int value) {
      this.value = value;
    }
  }

  public void set(Status status){
    byte[] buffer = new byte[]{(byte)status.value};
    this.serial.write(buffer, 1);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
