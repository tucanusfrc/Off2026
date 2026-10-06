package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoPositionDrive;
import frc.robot.commands.AutoTurnDrive;
import frc.robot.commands.CatchnShoot;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.ConvenioSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeShooter;

public class AutoTeste1 extends SequentialCommandGroup{

    public AutoTeste1(DriveSubsystem drive, ClimberSubsystem climber, IntakeShooter intakeShooter, ConvenioSubsystem convenioSubsystem){
        addCommands(
            new CatchnShoot(intakeShooter, convenioSubsystem).withTimeout(2)
        );
    }   
}