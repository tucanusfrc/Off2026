package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConvenioSubsystem;

public class ConvenioOut extends Command{

    private ConvenioSubsystem convenioSubsystem;

    public ConvenioOut(ConvenioSubsystem convenioSubsystem){
        this.convenioSubsystem = convenioSubsystem;

        addRequirements(convenioSubsystem);
    }

    @Override
    public void initialize(){}
    
    @Override
    public void execute(){
        convenioSubsystem.out();
    }
    
    @Override
    public void end(boolean interrupted){
        convenioSubsystem.stop();
    }

}
