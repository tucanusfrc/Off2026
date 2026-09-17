package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase{

    private SparkMax m_climber = new SparkMax(20, MotorType.kBrushed);
    private SparkClosedLoopController c_loop_climber;
    private SparkMaxConfig c_climber = new SparkMaxConfig();
    private RelativeEncoder encoder_climber = m_climber.getEncoder();

    private double closePosition = 0;
    private double openPosition = -2.7;
    private double defaulGamePosition = 0;

    public ClimberSubsystem(){
        c_climber.inverted(false).closedLoop
                                .p(0.5)
                                .i(0)
                                .d(0);

        m_climber.configure(
            c_climber,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters
        );

        encoder_climber.setPosition(0);
        c_loop_climber = m_climber.getClosedLoopController();

        
    }

    public double getEncoderValue(){
        return encoder_climber.getPosition();
    }

    public double getCloseValue(){
        return closePosition;
    }

    public void closeClimber(){
        c_loop_climber.setSetpoint(closePosition, ControlType.kPosition);
    }

    public double getOpenValue(){
        return openPosition;
    }

    public void openClimber(){
        c_loop_climber.setSetpoint(openPosition, ControlType.kPosition);
    }

    public double getDefaultGameValue(){
        return defaulGamePosition;
    }

    public void defaultGame(){
        c_loop_climber.setSetpoint(defaulGamePosition, ControlType.kPosition);
    }

    public void stop(){
        m_climber.stopMotor();
    }
    
}
