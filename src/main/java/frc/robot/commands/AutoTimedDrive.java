package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class AutoTimedDrive extends Command{

    private DriveSubsystem drive;
    private final double speed;
    
    public AutoTimedDrive(DriveSubsystem drive, double speed){
        this.drive = drive;
        this.speed = speed;
        addRequirements(drive);
    }

    @Override
    public void initialize(){
        drive.setAllZeroEncoder();
    }

    @Override
    public void execute(){
        drive.autoSpeedDrive(speed);;
    }

    @Override
    public void end(boolean interrupted){
        drive.stop();
    }


}
