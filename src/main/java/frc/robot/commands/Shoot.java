package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeShooter;

public class Shoot extends Command {
    
    private IntakeShooter intakeShooter;

    public Shoot(IntakeShooter intakeShooter){
        this.intakeShooter = intakeShooter;

        addRequirements(intakeShooter);
    }

    @Override
    public void initialize(){}

    @Override
    public void execute(){
        intakeShooter.pegarAtirar();
    }

    @Override
    public void end(boolean interrupted){
        intakeShooter.stop();
    }

}
