package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConvenioSubsystem;
import frc.robot.subsystems.IntakeShooter;

public class Catch extends Command{

    private IntakeShooter intakeShooter;
    private ConvenioSubsystem convenioSubsystem;

    public Catch(IntakeShooter intakeShooter, ConvenioSubsystem convenioSubsystem){
        this.intakeShooter = intakeShooter;
        this.convenioSubsystem = convenioSubsystem;

        addRequirements(intakeShooter, convenioSubsystem);
    }

    @Override
    public void initialize(){}
    
    @Override
    public void execute(){
        intakeShooter.pegarAtirar();
        convenioSubsystem.in();
    }
    
    @Override
    public void end(boolean interrupted){
        intakeShooter.stop();
        convenioSubsystem.stop();
    }

}
