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
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Odometry.Odometry;
import frc.robot.Odometry.UpdateOdometryPos;
import frc.robot.Triggers.GetChassisControl;
import frc.robot.commands.Autonomous.AutoEmpty;
import frc.robot.commands.Autonomous.AutoPlateLeft;
import frc.robot.commands.Autonomous.AutoPlateMid;
import frc.robot.commands.Autonomous.AutoPlateRight;
import frc.robot.commands.Autonomous.AutoRetrivePlate;
import frc.robot.commands.Autonomous.AutonomousInit;
import frc.robot.commands.Chassis.BumpBack;
import frc.robot.commands.Chassis.ClimbHigh;
import frc.robot.commands.Chassis.HoldChassis;
import frc.robot.commands.Chassis.PosDrive;
import frc.robot.commands.Chassis.PrepClimbHigh;
import frc.robot.commands.Chassis.TurnTo;
import frc.robot.commands.Elevator.CalibrateElevator;
import frc.robot.commands.Elevator.ElevateTo;
import frc.robot.commands.Elevator.ElevateTo_NEW;
import frc.robot.commands.Elevator.LockClimber;
import frc.robot.commands.Elevator.ReleaseClimber;
import frc.robot.commands.Intake_Holder.CalibrateHolder;
import frc.robot.commands.PlateDispenser.RetractPH;
import frc.robot.commands.PlateDispenser.initPlate;
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
  
  SendableChooser<CommandGroup> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {


    
  CameraServer.getInstance().startAutomaticCapture();  
    //server.startAutomaticCapture(camera_B);
    m_chooser.addOption("Auto Right Plate", new AutoPlateRight());
    m_chooser.addOption("Auto Left Plate", new AutoPlateLeft());
    m_chooser.addDefault("Empty Auto",new AutoEmpty());
    m_chooser.addOption("AUTO MID", new AutoPlateMid());
    SmartDashboard.putData(m_chooser);

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
    m_chooser.addOption("Auto Right Plate", new AutoPlateRight());
    m_chooser.addOption("Auto Left Plate", new AutoPlateLeft());
    m_chooser.addDefault("Empty Auto",new AutoEmpty());
    m_chooser.addOption("AUTO MID", new AutoPlateMid());
    SmartDashboard.putData(m_chooser);
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
    //m_autonomousCommand = m_chooser.getSelected();
    m_autonomousCommand = new AutoPlateMid();
    elevator.stop();
    elevator.clearEncoder();//Only for tests
    elevator.clearI();
    new CalibrateHolder().start();
    this.gyro.reset();
    odometry = new Odometry(0.02, chassis.getEncoderValue()[0][0], chassis.getEncoderValue()[0][1]);
    odometry_notifier = new Notifier(odometry);
    odometry_notifier.startPeriodic(0.02);

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
    SmartDashboard.putData(m_chooser);
    if (m_autonomousCommand != null){
    m_autonomousCommand.cancel();
    }
    elevator.stop();
    elevator.clearI();
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
    SmartDashboard.putData("Plate",new initPlate());
    SmartDashboard.putData("Reset Holder",new CalibrateHolder());
    SmartDashboard.putData("ClimbHigh",new ClimbHigh());
    SmartDashboard.putData("PREP ClimbHigh",new PrepClimbHigh());
    SmartDashboard.putData("RELEASEClimb",new ReleaseClimber());
    SmartDashboard.putData("LOCK_climb",new LockClimber());
    SmartDashboard.putData("LOCK_CHASSIS",new HoldChassis());
    SmartDashboard.putData("Auto Plate",new AutoRetrivePlate());
    SmartDashboard.putData("Update Odometry",new frc.robot.Odometry.UpdateOdometryPos());
    SmartDashboard.putData("CALIBRATE ELEVATOR",new CalibrateElevator());
    SmartDashboard.putData("Retract PH",new RetractPH());
    SmartDashboard.putData("test High",new AutonomousInit());
    SmartDashboard.putData("Vision Test - Update Odometry",new UpdateOdometryPos());
  }


private void log(){
    double heading = (Math.toRadians(-gyro.getYaw()) + Math.PI/2);
    heading = Math.atan2(Math.sin(heading),Math.cos(heading));
    SmartDashboard.putNumber("Heading",heading);
    SmartDashboard.putNumber("X",odometry.get()[0]);
    SmartDashboard.putNumber("Y",odometry.get()[1]);
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
