// Copyright (c) 2017 FIRST. All rights reserved.

package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Basic Drive Mode Mecanum", group="Iterative Opmode")
@Disabled
public class BasicOpMode_Iterative extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor hubOneMotorOne = null;
    private DcMotor hubOneMotorTwo = null;
    private DcMotor hubOneMotorThree = null;
    private DcMotor hubOneMotorFour = null;

    // Code to run ONCE when the driver hits INIT
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables.
        hubOneMotorZero = hardwareMap.get(DcMotor.class, "hubOneMotorZero");
        hubOneMotorOne = hardwareMap.get(DcMotor.class, "hubOneMotorOne");
        hubOneMotorTwo = hardwareMap.get(DcMotor.class, "hubOneMotorTwo");
        hubOneMotorThree = hardwareMap.get(DcMotor.class, "hubOneMotorThree");

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
        double zeroPower;
        double onePower;
        double twoPower;
        double threePower;

        // Set the power for mecanum wheels
        zeroPower = gamepad1.left_stick_y - gamepad1.left_stick_x;
        onePower = gamepad1.left_stick_y + gamepad1.left_stick_x;
        twoPower = gamepad1.left_stick_y + gamepad1.left_stick_x;
        threePower = gamepad1.left_stick_y - gamepad1.left_stick_x;

        // Clip the power to be within -1 to 1 bounds
        zeroPower  = Range.clip(zeroPower, -1, 1);
        onePower   = Range.clip(onePower, -1, 1);
        twoPower   = Range.clip(twoPower, -1, 1);
        threePower = Range.clip(threePower, -1, 1);
        
    	// Send calculated power to wheels
    	hubOneMotorZero.setPower(zeroPower);
    	hubOneMotorOne.setPower(onePower);
    	hubOneMotorTwo.setPower(twoPower);
    	hubOneMotorThree.setPower(threePower);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "zero (%.2f), two (%.2f), three (%.2f), four (%.2f)",
                          hubOneMotorZero, hubOneMotoOne, hubOneMotorTwo, hubOneMotorThree);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
