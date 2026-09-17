package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoPositionDrive;
import frc.robot.commands.AutoTurnDrive;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveSubsystem;

public class AutoTeste1 extends SequentialCommandGroup{

    public AutoTeste1(DriveSubsystem drive, ClimberSubsystem climber){
        addCommands(
            new AutoPositionDrive(drive, 4),
            new AutoTurnDrive(drive, 90)
        );
    }   
}