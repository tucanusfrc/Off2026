// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ManualDrive;
import frc.robot.commands.OpenStateClimber;
import frc.robot.commands.Shoot;
import frc.robot.autos.AutoTeste1;
import frc.robot.commands.AutoPositionDrive;
import frc.robot.commands.AutoTurnDrive;
import frc.robot.commands.Catch;
import frc.robot.commands.CatchnShoot;
import frc.robot.commands.CloseStateClimber;
import frc.robot.commands.ConvenioOut;
import frc.robot.commands.Drop;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.ConvenioSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeShooter;
import frc.robot.subsystems.PigeonSubsystem;

public class RobotContainer {

  DriveSubsystem drive = new DriveSubsystem();
  IntakeShooter intakeShooter = new IntakeShooter();
  ConvenioSubsystem convenioSubsystem = new ConvenioSubsystem();
  ClimberSubsystem climber = new ClimberSubsystem();
  PigeonSubsystem pigeon = new PigeonSubsystem();

  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  XboxController drive_Controller = new XboxController(0);
  XboxController mec_Controller = new XboxController(1);

  public RobotContainer() {

    autoChooser.setDefaultOption("vai e volt", new AutoTeste1(drive, climber));

    drive.setDefaultCommand(new ManualDrive(drive, drive_Controller));

    //configureTeleOpBindings();
    configureBindings();

  }

  private void configureBindings() {

    SmartDashboard.putNumber("pideon yaw", pigeon.getYaw());
    

  }

  public void configureTeleOpBindings(){
    if (DriverStation.isTeleopEnabled()){
      drive.setDefaultCommand(new ManualDrive(drive, drive_Controller));

      new JoystickButton(mec_Controller, XboxController.Button.kX.value)
        .whileTrue(new Drop(intakeShooter, convenioSubsystem)
      );
      
      new JoystickButton(mec_Controller, XboxController.Button.kY.value)
        .whileTrue(new Shoot(intakeShooter)
      );
      
      new JoystickButton(mec_Controller, XboxController.Button.kB.value)
        .whileTrue(new ConvenioOut(convenioSubsystem)
      );
      
      new JoystickButton(mec_Controller, XboxController.Button.kA.value)
        .whileTrue(new Catch(intakeShooter, convenioSubsystem)
      );

      new JoystickButton(mec_Controller, XboxController.Button.kLeftBumper.value)
        .onTrue(new OpenStateClimber(climber)
      );

      new JoystickButton(mec_Controller, XboxController.Button.kRightBumper.value)
        .onTrue(new CloseStateClimber(climber)
      );
    }
  }

  public void configureTestBindings(){
    if (DriverStation.isTestEnabled()){

      drive.setDefaultCommand(new ManualDrive(drive, drive_Controller));

      new JoystickButton(drive_Controller, XboxController.Button.kX.value)
        .whileTrue(new Drop(intakeShooter, convenioSubsystem)
      );
      
      new JoystickButton(drive_Controller, XboxController.Button.kY.value)
        .whileTrue(new Shoot(intakeShooter)
      );
      
      new JoystickButton(drive_Controller, XboxController.Button.kB.value)
        .whileTrue(new ConvenioOut(convenioSubsystem)
      );
      
      new JoystickButton(drive_Controller, XboxController.Button.kA.value)
        .whileTrue(new Catch(intakeShooter, convenioSubsystem)
      );

      new JoystickButton(drive_Controller, XboxController.Button.kLeftBumper.value)
        .onTrue(new OpenStateClimber(climber)
      );

      new JoystickButton(drive_Controller, XboxController.Button.kRightBumper.value)
        .onTrue(new CloseStateClimber(climber)
      );

    }
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
