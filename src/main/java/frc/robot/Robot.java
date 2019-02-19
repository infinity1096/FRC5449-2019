/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import com.kauailabs.navx.frc.AHRS;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.UsbCameraInfo;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Odometry.Odometry;
import frc.robot.Triggers.GetChassisControl;
import frc.robot.commands.Autonomous.AutoRetrivePlate;
import frc.robot.commands.Chassis.BumpBack;
import frc.robot.commands.Chassis.ClimbHigh;
import frc.robot.commands.Chassis.HoldChassis;
import frc.robot.commands.Chassis.PosDrive;
import frc.robot.commands.Chassis.TurnTo;
import frc.robot.commands.Elevator.ElevateTo;
import frc.robot.commands.Elevator.LockClimber;
import frc.robot.commands.Elevator.ReleaseClimber;
import frc.robot.commands.Intake_Holder.CalibrateHolder;
import frc.robot.commands.Odometry.UpdateOdometryPos;
import frc.robot.subsystems.Arduino;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Holder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.PlateDispenser;
import frc.robot.subsystems.Pusher;
import frc.robot.subsystems.Arduino.Status;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {
  public static Chassis chassis = new Chassis();
  public static PlateDispenser platedispenser = new PlateDispenser();
  public static Holder holder = new Holder();
  public static Intake intake = new Intake();
  public static Elevator elevator = new Elevator();
  public static Pusher pusher = new Pusher();
  public static Arduino arduino = new Arduino();
  public static AHRS gyro = new AHRS(Port.kMXP);
  public static OI oi = new OI();
  public static GetChassisControl getchassiscontrol = new GetChassisControl();
  public static Odometry odometry;
  Notifier odometry_notifier;
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    getchassiscontrol.whenActive(new HoldChassis());
    //CameraServer.getInstance().startAutomaticCapture();
    /*
    Timer timer = new Timer();
    timer.reset();
    timer.start();
    pfr.Setaddress("home/lvuser/frc/Profile.csv");
    new Thread(pfr).start();
    while(!pfr.is_finished()){
    }
    timer.stop();
    System.out.println("Time" + String.valueOf(timer.get()));
    System.out.println("Count" + String.valueOf(pfr.getCount()));
    SmartDashboard.putData("Auto mode", m_chooser);
    */
    //Notifier notifier = new Notifier(om);
    //notifier.startPeriodic(0.02);

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    arduino.set(Status.kDISABLED);
    if (odometry_notifier != null){
      this.odometry_notifier.stop();
    }
  }

  @Override
  public void disabledPeriodic() {
    arduino.set(Status.kDISABLED);
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    elevator.stop();
    elevator.clearEncoder();//Only for tests
    elevator.clearI();
    new CalibrateHolder().start();
    this.gyro.reset();
    odometry = new Odometry(0.02, chassis.getEncoderValue()[0][0], chassis.getEncoderValue()[0][1]);
    odometry_notifier = new Notifier(odometry);
    odometry_notifier.startPeriodic(0.02);

  }

  /**
   * This function is called periodically during operator control.
   */

  boolean is_last_hold = false;
  boolean is_on = false;

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    log();
    double val = -oi.stick0.getRawAxis(3);

    SmartDashboard.putData("0",new TurnTo(0));
    SmartDashboard.putData("pi",new TurnTo(Math.PI/2));
    SmartDashboard.putData("Climb Ready",new ElevateTo(1650));
    SmartDashboard.putData("ClimbHigh",new ClimbHigh());
    SmartDashboard.putData("ClimbLow",new ClimbHigh());
    SmartDashboard.putData("RELEASEClimb",new ReleaseClimber());
    SmartDashboard.putData("LOCK_climb",new LockClimber());
    SmartDashboard.putData("LOCK_CHASSIS",new HoldChassis());
    SmartDashboard.putData("Auto Plate",new AutoRetrivePlate());
    SmartDashboard.putData("Update Odometry",new UpdateOdometryPos());
  }


private void log(){
    double heading = (Math.toRadians(-gyro.getYaw()) + Math.PI/2);
    heading = Math.atan2(Math.sin(heading),Math.cos(heading));
    SmartDashboard.putNumber("Heading",heading);
    SmartDashboard.putNumber("X",odometry.get()[0]);
    SmartDashboard.putNumber("Y",odometry.get()[1]);
    SmartDashboard.putNumber("Tilt", gyro.getRoll());
    double[] testarray = SmartDashboard.getNumberArray("testarray",new double[]{0});
    SmartDashboard.putNumber("testNum",testarray[0]);
    SmartDashboard.putNumber("Chassis Current LEFT", chassis.getCurrent()[0]);
    SmartDashboard.putNumber("Chassis Current RIGHT", chassis.getCurrent()[1]);
  }

  /**
   * 
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
