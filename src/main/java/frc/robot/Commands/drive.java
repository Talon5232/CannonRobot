// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.driveSub;

public class drive extends CommandBase {
  /** Creates a new drive. */
  private driveSub m_drive;
  private double joystickY;
  private double joystickZ;
  public drive(driveSub m_drive, double joystickY, double joystickZ) {
    this.m_drive = m_drive;
    addRequirements(m_drive);
    this.joystickY = joystickY;
    this.joystickZ = joystickZ;
   
  }

 
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    //Calls the drive command in driveSub to make the method a command to be ran as default command in RobotContainer
    m_drive.driveCommand(joystickY, joystickZ);
  }

 
  @Override
  public void end(boolean interrupted) {}

 
  @Override
  public boolean isFinished() {
    return false;
  }
}
