// Copyright (c) 2017 FIRST. All rights reserved.

package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Basic Drive Mode", group="Iterative Opmode")
@Disabled
public class BasicOpMode_Iterative extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
	private DcMotor launcher = null;

    // Code to run ONCE when the driver hits INIT
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables.
        hubOneMotorZero = hardwareMap.get(DcMotor.class, "hubOneMotorZero");
        hubOneMotorOne = hardwareMap.get(DcMotor.class, "hubOneMotorOne");
        hubOneMotorTwo = hardwareMap.get(DcMotor.class, "hubOneMotorTwo");
        hubOneMotorThree = hardwareMap.get(DcMotor.class, "hubOneMotorThree");
		hubOneMotorFour = hardwareMap.get(Dcmotor.class, "hubOneMotorFour");

        // Make sure motors are going in the right diretion

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    // Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
    @Override
    public void init_loop() {
    }

    // Code to run ONCE when the driver hits PLAY
    @Override
    public void start() {
        runtime.reset();
    }

    // Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry
        double leftPower;
        double rightPower;
		double hubOneMotorFour;

    	// Send calculated power to wheels
        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Launcher", "Launcher speed(%.2f)", hubOneMotorFour);


		if(gamepad1.a) {
			hubOneMotorFour = .75;
			if (gamepad1.dpad_up) {
				hubOneMotorFour += .05;
			} else if (gamepad1.dpad_down) {
				hubOneMotorFour -= .05;
			}
		}
			
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
