// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.cannonSub;

public class cannonRefill extends CommandBase {
  /** Creates a new cannonRefill. */
  private cannonSub m_cannon;
  private boolean finished = false;
  public cannonRefill(cannonSub m_cannon) {
    this.m_cannon = m_cannon;
    addRequirements(m_cannon);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.m_cannon.disableCannon();
    finished = false;
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.m_cannon.refillCannons();
    //Constants are good!
   if(this.m_cannon.compressor1pressure() >= m_cannon.getPressureSetpoint()){
     finished = true;
   }
  }
    
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.m_cannon.cannonleftshot = false;
    this.m_cannon.pressureAmountSet = false;
    this.m_cannon.cannonrightshot = false;
   this.m_cannon.disableCompressors();
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
