// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class cannonSub extends SubsystemBase {
  /** Creates a new cannonSub. */
 private final Solenoid solenoid1 = new Solenoid(11,PneumaticsModuleType.REVPH, Constants.solinoid1);
 //private final Solenoid solenoid2 = new Solenoid(11,PneumaticsModuleType.REVPH, Constants.solinoid2);
// private final DoubleSolenoid solenoid1 = new DoubleSolenoid(11, PneumaticsModuleType.REVPH, Constants.solinoid3, Constants.solinoid1);
 //private final DoubleSolenoid solenoid2 = new DoubleSolenoid(11, PneumaticsModuleType.REVPH, Constants.solinoid4, Constants.solinoid2); 
  private final Compressor compressor1 = new Compressor(11, PneumaticsModuleType.REVPH);
  private final WPI_TalonSRX solenoid3 = new WPI_TalonSRX(12);
  private final WPI_TalonSRX solenoid4 = new WPI_TalonSRX(13);
  private final WPI_TalonFX cannonMotor = new WPI_TalonFX(Constants.cannonMotor);
  private final PneumaticHub hub = new PneumaticHub();
  private int setpoint;
  
  public boolean cannon1shot = true;
  public double preasure = 0;
  public double maxpreassure = 110;
  private double error = 0;
  public boolean cannon2shot = true;
  private double pm = .0005;
  

  
  public cannonSub() {
   solenoid3.configPeakCurrentLimit(1);
   solenoid3.configContinuousCurrentLimit(1);
   solenoid4.configPeakCurrentLimit(1);
   solenoid4.configContinuousCurrentLimit(1);

  //cannonMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void shootCannon1(){
   // solenoid1.set(Value.kForward);
   // solenoid2.set(Value.kForward);
   solenoid3.setVoltage(13);
   solenoid4.setVoltage(13);
   // solenoid1.set(true);
    cannon1shot = true;
  }
  public void compressoron(){
    compressor1.enableDigital();
  }
  public void shootCannon2(){
    //solenoid2.set(Value.kForward);
   // solenoid2.set(Value.kReverse);
   // solenoid1.set(Value.kReverse);
  solenoid4.setVoltage(0);
  solenoid3.setVoltage(0);
   // solenoid2.set(true);
   // solenoid2.toggle();
    cannon2shot = true;
  }

  public void disableCannon(){
    //solenoid1.set(Value.kReverse);
    //solenoid2.set(Value.kReverse);
   solenoid3.setVoltage(0);
  solenoid4.setVoltage(0);
   //  solenoid2.set(false);
  }

  

  public void cannonup(){
   setpoint = setpoint + 10;
  }

  public void cannonDown(){
   setpoint = setpoint -10;
  }




  public void refillCannons(){
    //Both tanks must be shot before refilling to ensure not overfilling one tank
    if(cannon1shot == true && cannon2shot == true){
      compressor1.enableAnalog(Constants.minPressure, Constants.maxPressure);
    }
  }

  public void refillCannonShort(){}
  public void disableCompressors(){
   compressor1.disable();
  }
  
  public double compressor1pressure(){
    return compressor1.getPressure();
  }

  public void SmartDashBoardData(){
    SmartDashboard.putNumber("Compressor1 Pressure", compressor1pressure());
    //SmartDashboard.putNumber("Pressure", hub.getPressure(0));
    SmartDashboard.putBoolean("Solinoid1", solenoid1.get());
  //  SmartDashboard.putBoolean("Solinoid2", solenoid2.get());
    SmartDashboard.putNumber("Compressor`0 Pressure", compressor1.getPressure());
    SmartDashboard.putNumber("Compressor1 Voltage", compressor1.getAnalogVoltage());
    SmartDashboard.putNumber("Encoder", cannonMotor.getSelectedSensorPosition());
    SmartDashboard.putNumber("MotorPower", error);
  }
  
  @Override
  public void periodic() {
   error = setpoint - cannonMotor.getSelectedSensorPosition();
   if(error >= .2){
    error = .2;
   }
   else if (error <= -.2){
    error = -.2;
   }
   //cannonMotor.set(error);

   SmartDashBoardData();

  }
}
