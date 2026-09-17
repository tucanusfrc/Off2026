package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeShooter extends SubsystemBase{
    
    private SparkMax m_shooterLeft = new SparkMax(22, MotorType.kBrushless);
    private SparkMax m_shooterRight = new SparkMax(21, MotorType.kBrushless);

    private SparkMaxConfig c_shLeft = new SparkMaxConfig();
    // private SparkMaxConfig c_shRight = new SparkMaxConfig();

    public IntakeShooter (){
        c_shLeft.inverted(true);

        m_shooterLeft.configure( 
                        c_shLeft,
                        ResetMode.kResetSafeParameters,
                        PersistMode.kPersistParameters
        );
    }

    public void Ativar(double speed){
        m_shooterLeft.set(speed);
        m_shooterRight.set(-speed);
    }

    public void pegarAtirar(){
        m_shooterLeft.setVoltage(12);;
        m_shooterRight.setVoltage(12);;
    }

    public void soltar(){
        m_shooterLeft.setVoltage(12);;
        m_shooterRight.setVoltage(12);;
    }

    public void stop(){
        m_shooterLeft.stopMotor();
        m_shooterRight.stopMotor();
    }

}
