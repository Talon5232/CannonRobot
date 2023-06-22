// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class cannonSub extends SubsystemBase {
  /** Creates a new cannonSub. */
  private final DoubleSolenoid solenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 1, 2);
  private final Compressor compressor1 = new Compressor(1, PneumaticsModuleType.REVPH);
  private final Compressor compressor2 = new Compressor(2, PneumaticsModuleType.REVPH);
  private final WPI_TalonFX cannonMotor = new WPI_TalonFX(Constants.cannonMotor);
  
  public boolean cannon1shot = true;
  public boolean cannon2shot = true;

  
  public cannonSub() {

  }

  public void shootCannon1(){
    solenoid.set(Value.kForward);
    cannon1shot = true;
  }

  public void shootCannon2(){
    solenoid.set(Value.kReverse);
    cannon2shot = true;
  }

  public void disableCannon(){
    solenoid.set(Value.kOff);
  }

  public void cannonMotorStop(){
    cannonMotor.set(0);
  }

  public void cannonup(){
    cannonMotor.set(.2);
  }

  public void cannonDown(){
    cannonMotor.set(-.2);
  }


  public void refillCannons(){
    //Both tanks must be shot before refilling to ensure not overfilling one tank
    if(cannon1shot == true && cannon2shot == true){
      compressor1.enableAnalog(Constants.minPressure, Constants.maxPressure);
      compressor2.enableDigital();
    }
  }
  public void disableCompressors(){
    compressor1.disable();
    compressor2.disable();
  }

  public double compressor1pressure(){
    return compressor1.getPressure();
  }

  public double compressor2pressure(){
    return compressor2.getPressure();
  }

  public void SmartDashBoardData(){
    SmartDashboard.putNumber("Compressor1 Pressure", compressor1pressure());
    SmartDashboard.putNumber("Compressor2 Pressure", compressor2pressure());
    SmartDashboard.putNumber("Compressor1 Voltage", compressor1.getAnalogVoltage());
    SmartDashboard.putNumber("Compressor2 Voltage", compressor2.getAnalogVoltage());
  }
  @Override
  public void periodic() {
    SmartDashBoardData();
  }
}
