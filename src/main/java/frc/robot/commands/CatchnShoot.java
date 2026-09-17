package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConvenioSubsystem;
import frc.robot.subsystems.IntakeShooter;

public class CatchnShoot extends Command {
    
    private IntakeShooter intakeShooter;
    private ConvenioSubsystem convenioSubsystem;

    public CatchnShoot(IntakeShooter intakeShooter, ConvenioSubsystem convenioSubsystem){
        this.intakeShooter = intakeShooter;
        this.convenioSubsystem = convenioSubsystem;

        addRequirements(intakeShooter, convenioSubsystem);
    }

    @Override
    public void initialize(){}

    @Override
    public void execute(){
        intakeShooter.pegarAtirar();
        convenioSubsystem.out();
    }

    @Override
    public void end(boolean interrupted){
        intakeShooter.stop();
        convenioSubsystem.stop();
    }

}
