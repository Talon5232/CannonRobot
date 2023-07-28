// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Commands.cannonRefill;
import frc.robot.Commands.drive;
import frc.robot.Subsystems.*;

public class RobotContainer {

  private final Joystick xbox = new Joystick(0);

  private final int joystickY = XboxController.Axis.kLeftY.value;
  private final int joystickZ = XboxController.Axis.kRightX.value;
  private final JoystickButton Ybutton = new JoystickButton(xbox, XboxController.Button.kY.value);
  private final JoystickButton Bbutton = new JoystickButton(xbox, XboxController.Button.kB.value);
  private final JoystickButton Abutton = new JoystickButton(xbox, XboxController.Button.kA.value);
  private final JoystickButton Xbutton = new JoystickButton(xbox, XboxController.Button.kX.value);
  private final JoystickButton startButton = new JoystickButton(xbox, XboxController.Button.kStart.value);

  private final POVButton leftDpad = new POVButton(xbox, 270);
  private final POVButton upDpad = new POVButton(xbox, 0);
  private final POVButton downDpad = new POVButton(xbox, 180);



  private final JoystickButton rightbumperbutton = new JoystickButton(xbox, XboxController.Button.kRightBumper.value);
  private final JoystickButton leftbumperbutton = new JoystickButton(xbox, XboxController.Button.kLeftBumper.value);


  private final driveSub m_drive = new driveSub();
  private final cannonSub m_cannon = new cannonSub();


  public RobotContainer() {
    m_drive.setDefaultCommand(new drive(m_drive, () -> -xbox.getRawAxis(joystickY), () -> xbox.getRawAxis(joystickZ)));

    configureBindings();
  }
    
  

  private void configureBindings() {
   Ybutton.onTrue(new cannonRefill(m_cannon));

   rightbumperbutton.whileTrue(new InstantCommand(() -> m_cannon.rightCannonShoot()));

   leftbumperbutton.whileTrue(new InstantCommand(() -> m_cannon.leftCannonShoot()));

   Abutton.whileTrue(new InstantCommand(() -> m_cannon.cannonDown()));

   Bbutton.whileTrue(new InstantCommand(() -> m_cannon.cannonup()));

   Xbutton.onTrue(new InstantCommand(() -> m_cannon.disableCompressors()));

   downDpad.onTrue(new InstantCommand(() -> m_cannon.lowerPressure()));

   leftDpad.onTrue(new InstantCommand(() -> m_cannon.middlePressure()));

   upDpad.onTrue(new InstantCommand(() -> m_cannon.upperPressure()));
    
   startButton.onTrue(new InstantCommand(() -> m_cannon.cannonEncoderReset()));
  }


  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
