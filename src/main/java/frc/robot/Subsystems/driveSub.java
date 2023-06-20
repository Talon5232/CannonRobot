// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class driveSub extends SubsystemBase {
  //Set the ID of motors using constant class
  private final WPI_VictorSPX leftmotor1 = new WPI_VictorSPX(Constants.leftmotor1);
  private final WPI_VictorSPX leftmotor2 = new WPI_VictorSPX(Constants.leftmotor2);
  private final WPI_TalonSRX rightmotor1 = new WPI_TalonSRX(Constants.rightmotor1);
  private final WPI_TalonSRX rightmotor2 = new WPI_TalonSRX(Constants.rightmotor2);
  private final DifferentialDrive drivetrain;
  
  public driveSub() {
    //Motor Delcaration
    MotorControllerGroup left = new MotorControllerGroup(leftmotor1, leftmotor2);
    MotorControllerGroup right = new MotorControllerGroup(rightmotor1,rightmotor2);
    right.setInverted(true);
     drivetrain = new DifferentialDrive(left, right);
    

  }
  public void driveCommand(double joystickY, double joystickZ){
    //Arcade Drive Command
    drivetrain.arcadeDrive(joystickY, joystickZ);

  }
}
