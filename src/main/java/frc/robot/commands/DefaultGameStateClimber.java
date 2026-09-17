package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimberSubsystem;

public class DefaultGameStateClimber extends Command {
    
    private ClimberSubsystem climberSubsystem;

    private final double climberErrorTolerance = 0.05;
    
    public DefaultGameStateClimber(ClimberSubsystem climberSubsystem){
        this.climberSubsystem = climberSubsystem;

        addRequirements(climberSubsystem);
    }

    @Override
    public void initialize(){}

    @Override
    public void execute(){
        climberSubsystem.defaultGame();
    }

    @Override
    public void end(boolean interrupted){
        climberSubsystem.stop();
    }

    @Override
    public boolean isFinished(){
        return Math.abs(climberSubsystem.getEncoderValue() - climberSubsystem.getDefaultGameValue()) > climberErrorTolerance ? false : true;
    }


}   
