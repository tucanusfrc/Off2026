package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class AutoTurnDrive extends Command {
    private DriveSubsystem drive;
    private final double graus;
    private final double erro = 0.01;
    
    public AutoTurnDrive(DriveSubsystem drive, double graus){
        this.drive = drive;
        this.graus = graus;
        addRequirements(drive);
    }

    @Override
    public void initialize(){
        drive.resetPigeonYaw();
    }

    @Override
    public void execute(){
        drive.autoTurn(graus);
    }

    @Override
    public void end(boolean interrupted){
        drive.stop();
    }

    @Override
    public boolean isFinished(){
        return Math.abs(drive.getPigeonYaw() - graus) > erro ? false : true;
    }
}
