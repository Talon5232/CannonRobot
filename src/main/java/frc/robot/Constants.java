// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Add your docs here. */
public class Constants {
//#region CanID
public static final int cannonMotor = 8;

public static final int compressor1 = 1;

public static final int compressor2 = 2;

public static final int cannonEncoder = 0;

public static final int leftCannonSolenoid = 12;

public static final int rightCannonSolenoid = 13;

public static final int leftmotor1 = 3;

public static final int leftmotor2 = 4;

public static final int rightmotor1 = 1;

public static final int rightmotor2 = 2;
//#endregion
//Change to Correct Value
public static final double cannonSetpointAdjustment = 0.03;

public static final double cannonKp = 2.5;

public static final double cannonKd = 1.75;

public static final double cannonEncoderMax = .1;

public static final double cannonEncoderMin = 0;
//14 too much
public static final int lowerPressure = 12;//used to be 12
//16 psi at top angle is perfect for candy it used to be 13
public static final int middlePressure = 14;

public static final int upperPressure = 15;


}
