// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class driveSub extends SubsystemBase {
  // Set the ID of motors using constant class
  private final WPI_TalonSRX leftmotor1 = new WPI_TalonSRX(Constants.leftmotor1);
  private final WPI_TalonSRX leftmotor2 = new WPI_TalonSRX(Constants.leftmotor2);
  private final WPI_VictorSPX rightmotor1 = new WPI_VictorSPX(Constants.rightmotor1);
  private final WPI_VictorSPX rightmotor2 = new WPI_VictorSPX(Constants.rightmotor2);
  private final DifferentialDrive drivetrain;

  public driveSub() {
    // Motor Delcaration
    MotorControllerGroup left = new MotorControllerGroup(leftmotor1, leftmotor2);
    MotorControllerGroup right = new MotorControllerGroup(rightmotor1, rightmotor2);
    right.setInverted(true);
    drivetrain = new DifferentialDrive(left, right);

  }

  public void driveCommand(DoubleSupplier joystickY, DoubleSupplier joystickZ) {
    // Arcade Drive Command
    SmartDashboard.putNumber("JoystickY", joystickY.getAsDouble());
    drivetrain.arcadeDrive(joystickY.getAsDouble(), joystickZ.getAsDouble());

  }
}
