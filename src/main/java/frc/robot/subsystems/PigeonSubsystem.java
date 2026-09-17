package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PigeonSubsystem extends SubsystemBase {
    
    private Pigeon2 pigeon2 = new Pigeon2(4);

    public PigeonSubsystem(){
    }

    public double getYaw(){
        return pigeon2.getYaw().getValueAsDouble();
    }

    public void resetYaw(){
        pigeon2.setYaw(0);
    }

}
