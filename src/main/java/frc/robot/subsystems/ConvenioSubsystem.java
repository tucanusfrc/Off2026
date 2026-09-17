package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ConvenioSubsystem extends SubsystemBase{
    
    private SparkMax m_convenio = new SparkMax(5, MotorType.kBrushed);
    private SparkMaxConfig c_convenio = new SparkMaxConfig();
    
    public ConvenioSubsystem(){
        c_convenio.idleMode(IdleMode.kCoast);

        m_convenio.configure(
                        c_convenio,
                        ResetMode.kResetSafeParameters,
                        PersistMode.kPersistParameters
        );
    }

    public void out(){
        m_convenio.set(1);
    }

    public void in(){
        m_convenio.set(-1);
    }

    public void stop(){
        m_convenio.stopMotor();
    }

}
