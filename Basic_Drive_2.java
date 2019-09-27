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
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Drive2", group="Iterative Opmode")
public class Basic_Drive_2 extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor right = null;
    private DcMotor left = null;
    private DcMotor climb = null;
    private DcMotor lift = null;
    private Servo dropper = null;
    private CRServo turn_table = null;
    private CRServo extension_arm = null;
    private Servo door = null;


    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        right = hardwareMap.get(DcMotor.class, "right");
        left = hardwareMap.get(DcMotor.class, "left");
        climb = hardwareMap.get(DcMotor.class, "climb");
        lift = hardwareMap.get(DcMotor.class, "lift");
        dropper = hardwareMap.get(Servo.class, "dropper");
        turn_table = hardwareMap.get(CRServo.class, "turn_table");
        extension_arm = hardwareMap.get(CRServo.class, "extension_arm");
        door = hardwareMap.get(Servo.class, "door");


        // Set the motor direction
        right.setDirection(DcMotor.Direction.FORWARD);
        left.setDirection(DcMotor.Direction.REVERSE);
        climb.setDirection(DcMotor.Direction.REVERSE);
        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }


    @Override
    public void stop() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    double speedFactor = 1;

    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry.
        double rightPower = 0;
        double leftPower = 0;
        double climbPower = 0;
        double rightTrigger = 0;


        //Button a will slow evermotor to half top speed in order to increase control.
        if (speedFactor == 1 && gamepad1.a) {
            speedFactor = 0.5;
        } else if (gamepad1.b) {
            speedFactor = 1;
        }

        if (speedFactor > 1) {
            speedFactor = 1;
        }


        //Set the  controls for tank driving and steering.
        rightPower = gamepad1.left_stick_y;
        leftPower = gamepad1.right_stick_y;

        //Right bumper and left bumper control the up and down of the climb arm.
        if (gamepad1.right_bumper) {
            climbPower += 1;
        }
        rightTrigger = gamepad1.right_trigger;
        if (rightTrigger > 0.5) {
            climbPower -= 1;
        }

        //x and y controller the servo to drop the team marker
        if (gamepad1.x) {
            dropper.setPosition(0);
        } else if (gamepad1.y) {
            dropper.setPosition(1);
        }


        //rotate the turn table
        turn_table.setPower(gamepad2.right_stick_x/2);


        //extend the arm
        extension_arm.setPower(gamepad2.left_stick_y);


        //lift the arm
        lift.setPower(gamepad2.right_stick_y/2);


        //open/close door
        if(gamepad2.right_stick_button) {
            door.setPosition(1);
        } else{
            door.setPosition(0.5);
        }

        // Send calculated power to motors
        right.setPower(rightPower * speedFactor);
        left.setPower(leftPower * speedFactor);
        climb.setPower(climbPower * speedFactor);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motor", "right (%.2f), left (%.2f)", rightPower, leftPower);
        telemetry.addData("Other Variables", "speed factor: (%.2f)", speedFactor);
    }

}