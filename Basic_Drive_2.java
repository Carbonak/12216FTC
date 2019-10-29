/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Drive2", group="Iterative Opmode")
public class Basic_Drive_2 extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontRight = null;
    private DcMotor frontLeft = null;
    private DcMotor backRight = null;
    private DcMotor backLeft = null;
    private Servo attatch = null;
    /*
    private Servo dropper = null;
    private CRServo turn_table = null;
    private CRServo extension_arm = null;
    private Servo door = null;
    private Servo light = null;
    private TouchSensor wall = null;*/


    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        frontRight = hardwareMap.get(DcMotor.class, "FR");
        frontLeft = hardwareMap.get(DcMotor.class,  "FL");
        backRight = hardwareMap.get(DcMotor.class, "BR");
        backLeft = hardwareMap.get(DcMotor.class, "BL");
        attatch = hardwareMap.get(Servo.class, "AT");
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop()
    {

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }


    @Override
    public void stop()
    {

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    double speedFactor = 1;

    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry.
        double frontRightPower = 0;
        double frontLeftPower = 0;
        double backRightPower = 0;
        double backLeftPower = 0;


        telemetry.addData("x", "%.2f", -gamepad1.right_stick_x);
        telemetry.addData("y", "%.2f", -gamepad1.right_stick_y);

        frontRightPower = gamepad1.right_stick_y;
        frontLeftPower = -gamepad1.right_stick_y;
        backLeftPower = -gamepad1.right_stick_y;
        backRightPower = gamepad1.right_stick_y;

        frontRightPower += gamepad1.right_stick_x;
        frontLeftPower += gamepad1.right_stick_x;
        backLeftPower += -gamepad1.right_stick_x;
        backRightPower += -gamepad1.right_stick_x;

        frontRightPower += -gamepad1.left_stick_x;
        frontLeftPower += -gamepad1.left_stick_x;
        backLeftPower += -gamepad1.left_stick_x;
        backRightPower += -gamepad1.left_stick_x;

        if(gamepad1.a) {
            attatch.setPosition(1);
        } else if (gamepad1.b) {
            attatch.setPosition(0.2);
        }


        // Send calculated power to motors
        frontRight.setPower(frontRightPower);
        frontLeft.setPower(frontLeftPower);
        backRight.setPower(backRightPower);
        backLeft.setPower(backLeftPower );

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motor", "frontRight (%.2f), frontLeft (%.2f), backRight (%.2f), backLeft (%.2f)", frontRightPower, frontLeftPower, backRightPower, backLeftPower);
        telemetry.addData("Other Variables", "speed factor: (%.2f)", speedFactor);
    }

}