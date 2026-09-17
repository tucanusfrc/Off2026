package frc.robot.commands;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class AutoPositionDrive extends Command{

    private DriveSubsystem drive;
    private final double metros;
    private static boolean finished = false;
    
    public AutoPositionDrive(DriveSubsystem drive, double metros){
        this.drive = drive;
        this.metros = metros;
        addRequirements(drive);
    }

    @Override
    public void initialize(){
        drive.setAllZeroEncoder();
    }

    @Override
    public void execute(){
        drive.autoPositionDrive(metros);

        if(metros < Math.abs(drive.getLeftRotationMeters())){
            finished = true;
        } else{
            finished = false;
        }
        System.out.println("comando 1");

    }

    @Override
    public void end(boolean interrupted){
        drive.stop();
    }

    @Override
    public boolean isFinished(){
        return finished;
    }

}
