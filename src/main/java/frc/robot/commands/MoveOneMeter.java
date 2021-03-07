/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class MoveOneMeter extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Chassis m_chassis;
  private int pulses_right;
  private int pulses_left;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public MoveOneMeter(Chassis subsystem) {
    m_chassis = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_chassis.power = 0.3;
    pulses_right = m_chassis.getSensorRight();
    pulses_left = m_chassis.getSensorLeft();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println(m_chassis.getSensorLeft() - pulses_left + " = Left");
    System.out.println(m_chassis.getSensorRight() - pulses_right + " = Right");
    double rPower = SmartDashboard.getNumber("right speed", 0);
    double lPower = SmartDashboard.getNumber("left speed", 0);
    m_chassis.setpower(44700, rPower, lPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_chassis.power = 0.0;
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if (m_chassis.getSensorLeft()-pulses_left >= Constants.onemeterinpulses || m_chassis.getSensorRight()-pulses_right >= Constants.onemeterinpulses) {
        return true;
    }
    return false;
  }
}