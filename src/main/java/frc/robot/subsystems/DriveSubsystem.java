package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
    

    private SparkMax m_left_Leader = new SparkMax(24, MotorType.kBrushless);
    private SparkMax m_left_Follower = new SparkMax(16, MotorType.kBrushless);
    private SparkMax m_right_Leader = new SparkMax(17, MotorType.kBrushless);
    private SparkMax m_right_Follower = new SparkMax(23, MotorType.kBrushless);

    private VictorSPX v_left_Leader = new VictorSPX(7);
    private VictorSPX v_left_Follower = new VictorSPX(8);
    private VictorSPX v_right_Leader = new VictorSPX(1);
    private VictorSPX v_right_Follower = new VictorSPX(5);

    private SparkMaxConfig c_leftFollower = new SparkMaxConfig();
    private SparkMaxConfig c_rightFollower = new SparkMaxConfig();
    private SparkMaxConfig c_leader = new SparkMaxConfig();


    private DifferentialDrive drive;

    private PigeonSubsystem pigeon = new PigeonSubsystem();

    private RelativeEncoder leftEncoder = m_left_Leader.getEncoder();
    private RelativeEncoder rightEncoder = m_right_Leader.getEncoder();

    private SparkClosedLoopController leftPositionController;
    private SparkClosedLoopController rightPositionController;

    private double tolerance = Constants.DriveTolerance;
    private double leftSpeedDrive;
    private double rightSpeedDrive;

    public DriveSubsystem(){
        c_leftFollower.follow(m_left_Leader);
        c_rightFollower.follow(m_right_Leader);
        c_leader.inverted(false).closedLoop
                                    .p(Constants.DrivePositionkP)
                                    .i(Constants.DrivePositionkI)
                                    .d(Constants.DrivePositionkD);

        m_left_Follower.configure(
                        c_leftFollower, 
                        ResetMode.kResetSafeParameters,
                        PersistMode.kPersistParameters
        );
        
        m_right_Follower.configure(
                        c_rightFollower, 
                        ResetMode.kResetSafeParameters,
                        PersistMode.kPersistParameters
        );
        
        m_left_Leader.configure(
                        c_leader, 
                        ResetMode.kResetSafeParameters,
                        PersistMode.kPersistParameters
        );

        m_right_Leader.configure(
                        c_leader, 
                        ResetMode.kResetSafeParameters,
                        PersistMode.kPersistParameters
        );

        leftPositionController = m_left_Leader.getClosedLoopController();
        rightPositionController = m_right_Leader.getClosedLoopController();

        
        drive = new DifferentialDrive(m_left_Leader, m_right_Leader);
    }
    

    public void manualDrive(double left_axis, double right_axis, double left_trigger, double right_trigger){

        if(left_trigger > tolerance || right_trigger > tolerance){
            leftSpeedDrive = left_trigger > right_trigger ? left_trigger : -right_trigger;
            rightSpeedDrive = left_trigger > right_trigger ? left_trigger : -right_trigger;
        } else{
            leftSpeedDrive = Math.abs(left_axis) > tolerance ? left_axis * Constants.DrivePower : 0;
            rightSpeedDrive = Math.abs(right_axis) > tolerance ? right_axis * Constants.DrivePower: 0;
        }

        // drive.tankDrive(-leftSpeedDrive, rightSpeedDrive);

        v_left_Leader.set(VictorSPXControlMode.PercentOutput, rightSpeedDrive);
        v_left_Follower.set(VictorSPXControlMode.PercentOutput, rightSpeedDrive);
        v_right_Leader.set(VictorSPXControlMode.PercentOutput, -leftSpeedDrive);
        v_right_Follower.set(VictorSPXControlMode.PercentOutput, -leftSpeedDrive);

    }

    public void autoTurn(double graus){
        if(graus > 0){
            autoRotationDrive((pigeon.getYaw() - graus)/90);
        } else{
            autoRotationDrive(-(pigeon.getYaw() - graus)/90);
        }
    }

    private double metersToRotation(double meters){
        return meters * Constants.MeterRotationConverser;
    }
    
    private double rotationtoMeters(double rotation){
        return rotation / Constants.MeterRotationConverser;
    }

    public void autoSpeedDrive(double speed){
        drive.arcadeDrive(0, speed);
    }

    public void autoRotationDrive(double rotation){
        drive.arcadeDrive(rotation, 0);
    }

    public void autoPositionDrive(double meters){
        double rotation = metersToRotation(meters);
        leftPositionController.setSetpoint(rotation, ControlType.kPosition);
        rightPositionController.setSetpoint(-rotation, ControlType.kPosition);
    }

    public double getLeftEncoder(){
        return leftEncoder.getPosition();
    }

    public double getLeftRotationMeters(){
        return rotationtoMeters(leftEncoder.getPosition());
    }

    public double getRightEncoder(){
        return rightEncoder.getPosition();
    }

    public double getRightRotationMeters(){
        return rotationtoMeters(rightEncoder.getPosition());
    }

    public double getPigeonYaw(){
        return pigeon.getYaw();
    }

    public void resetPigeonYaw(){
        pigeon.resetYaw();
    }

    public void setAllZeroEncoder(){
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }

    public void stop(){
        drive.stopMotor();
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("left drive encoder", getLeftEncoder());
        SmartDashboard.putNumber("right drive encoder", getRightEncoder());
        SmartDashboard.putNumber("right drive rotation", getRightRotationMeters());
        SmartDashboard.putNumber("left drive rotation", getLeftRotationMeters());
    }
}
