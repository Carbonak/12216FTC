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
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Drive", group="Iterative Opmode")
public class Basic_Drive extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontRight = null;
    private DcMotor frontLeft = null;
    private DcMotor backRight = null;
    private DcMotor backLeft = null;
    private DcMotor lift = null;
    private Servo claw = null;
    private Servo finger = null;
    private Servo finger2 = null;

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
        //lift = hardwareMap.get(DcMotor.class, "L");
        //claw = hardwareMap.get(Servo.class, "C");
        finger = hardwareMap.get(Servo.class, "F");
        finger2 = hardwareMap.get(Servo.class, "F2");
        telemetry.addData("Status", "Initialized");

        //reset encoder values
        //lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


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
        //double liftPower = 0;

        telemetry.addData("x", "%.2f", -gamepad1.right_stick_x);
        telemetry.addData("y", "%.2f", -gamepad1.right_stick_y);

        frontRightPower -= gamepad1.right_stick_y;
        frontLeftPower -= -gamepad1.right_stick_y;
        backLeftPower -= -gamepad1.right_stick_y;
        backRightPower -= gamepad1.right_stick_y;

        frontRightPower -= gamepad1.right_stick_x;
        frontLeftPower -= gamepad1.right_stick_x;
        backLeftPower -= -gamepad1.right_stick_x;
        backRightPower -= -gamepad1.right_stick_x;

        frontRightPower += -gamepad1.left_stick_x;
        frontLeftPower += -gamepad1.left_stick_x;
        backLeftPower += -gamepad1.left_stick_x;
        backRightPower += -gamepad1.left_stick_x;
/*
        if (gamepad1.right_trigger > 0.3){
            liftPower = -0.5;
        }else if (gamepad1.right_bumper) {
            liftPower = 0.5;
        } else {
            liftPower = 0;
        }

        // Claw Mechanism
        if(gamepad1.left_trigger > 0.1) {
            claw.setPosition(0);
        } else if (gamepad1.left_bumper){
            claw.setPosition(1);
        }
*/
        // Finger for the foundation
        if(gamepad1.a){
            finger.setPosition(1);
            finger2.setPosition(0);
        }else if (gamepad1.b){
            finger.setPosition(0.0);
            finger2.setPosition(1);
        }


        // Send calculated power to motors
        frontRight.setPower(frontRightPower);
        frontLeft.setPower(frontLeftPower);
        backRight.setPower(backRightPower);
        backLeft.setPower(backLeftPower );
        //lift.setPower(liftPower);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motor", "frontRight (%.2f), frontLeft (%.2f), backRight (%.2f), backLeft (%.2f)", frontRightPower, frontLeftPower, backRightPower, backLeftPower);
        telemetry.addData("Other Variables", "speed factor: (%.2f)", speedFactor);
    }

}