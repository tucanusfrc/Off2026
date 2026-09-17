package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class ManualDrive extends Command {

    private DriveSubsystem driveSubsystem;
    private XboxController controller;

    public ManualDrive(DriveSubsystem driveSubsystem, XboxController controller){
        this.controller = controller;
        this.driveSubsystem = driveSubsystem;

        addRequirements(driveSubsystem);
    }

    @Override
    public void execute(){
        driveSubsystem.manualDrive(
            controller.getLeftY(), controller.getRightY(),
            controller.getLeftTriggerAxis(), controller.getRightTriggerAxis()
        );
    }

    @Override
    public void end(boolean interrupted){
        driveSubsystem.stop();
    }

}
