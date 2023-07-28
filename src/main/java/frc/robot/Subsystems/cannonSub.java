// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class cannonSub extends SubsystemBase {
  /** Creates a new cannonSub. */
  private final Compressor compressor1 = new Compressor(11, PneumaticsModuleType.REVPH);
  private final DutyCycleEncoder cannonEncoder = new DutyCycleEncoder(Constants.cannonEncoder);
  private final WPI_TalonSRX leftCannonSolenoid = new WPI_TalonSRX(Constants.leftCannonSolenoid);
  private final WPI_TalonSRX rightCannonSolenoid = new WPI_TalonSRX(Constants.rightCannonSolenoid);
  private final WPI_TalonFX cannonMotor = new WPI_TalonFX(Constants.cannonMotor);
  private final PIDController pid = new PIDController(Constants.cannonKp, 0, Constants.cannonKd);
  public double setpoint = 0;
  public boolean cannonleftshot = true;
  public boolean cannonrightshot = true;
  public boolean pressureAmountSet = false;
  public double pressureSetpoint = 0;

  
  public cannonSub() {
   leftCannonSolenoid.configPeakCurrentLimit(1);
   leftCannonSolenoid.configContinuousCurrentLimit(1);
   rightCannonSolenoid.configPeakCurrentLimit(1);
   rightCannonSolenoid.configContinuousCurrentLimit(1);
  }
//#region Pnumatic Cannon Code
  public void leftCannonShoot(){
   leftCannonSolenoid.setVoltage(13);
   cannonleftshot = true;
  }
 
  public void rightCannonShoot(){
   rightCannonSolenoid.setVoltage(13);
  cannonrightshot = true;
  }

  public void disableCannon(){
   rightCannonSolenoid.setVoltage(0);
   leftCannonSolenoid.setVoltage(0);
  }

  public void lowerPressure(){
    pressureSetpoint = Constants.lowerPressure;
    pressureAmountSet = true;
  }

  public void middlePressure(){
    pressureSetpoint = Constants.middlePressure;
    pressureAmountSet = true;
  }

  public void upperPressure(){
    pressureSetpoint = Constants.upperPressure;
    pressureAmountSet = true;
  }

  public double getPressureSetpoint(){
    return pressureSetpoint;
  }
  public void refillCannons(){
    //Both tanks must be shot before refilling to ensure not overfilling one tank
    if(cannonrightshot == true && cannonleftshot == true && pressureAmountSet == true){
      compressor1.enableAnalog(pressureSetpoint-5, pressureSetpoint);
    }
  }

  public void disableCompressors(){
    rightCannonSolenoid.setVoltage(0);
    leftCannonSolenoid.setVoltage(0);
   compressor1.disable();
  }
  
  public double compressor1pressure(){
    return compressor1.getPressure();
  }
  //#endregion
//#region Cannon Motor Movement
  public void cannonup(){
    setpoint = setpoint + Constants.cannonSetpointAdjustment;
   }
 
   public void cannonDown(){
    setpoint = setpoint - Constants.cannonSetpointAdjustment;
   }

   public void cannonEncoderReset(){
    cannonEncoder.reset();
   }

  public void cannonMotorCorrection(){
    if (setpoint >= Constants.cannonEncoderMax){
      setpoint = Constants.cannonEncoderMax;
    }
    else if (setpoint <= Constants.cannonEncoderMin){
      setpoint = Constants.cannonEncoderMin;
    }
    cannonMotor.set(pid.calculate(cannonEncoder.getAbsolutePosition(), setpoint));
    
  }
  //#endregion

  public void SmartDashBoardData(){
    SmartDashboard.putNumber("Compressor1 Pressure", compressor1pressure());
    SmartDashboard.putNumber("Compressor`0 Pressure", compressor1.getPressure());
    SmartDashboard.putNumber("Compressor1 Voltage", compressor1.getAnalogVoltage());
    SmartDashboard.putNumber("Encoder", cannonMotor.getSelectedSensorPosition());
    SmartDashboard.putNumber("PressureSetpoint", getPressureSetpoint());
    
  }
  
  @Override
  public void periodic() {
   cannonMotorCorrection();
   SmartDashBoardData();

  }
}
