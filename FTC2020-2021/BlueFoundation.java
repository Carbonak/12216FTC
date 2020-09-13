package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="BlueFoundation", group="Linear Opmode")
public class BlueFoundation extends LinearOpMode
{

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontRight = null;
    private DcMotor frontLeft = null;
    private DcMotor backRight = null;
    private DcMotor backLeft = null;
    private Servo finger = null;
    private Servo finger2 = null;
    private Servo claw = null;


    @Override
        public void runOpMode() {

            telemetry.addData("Status", "Initialized");

            frontRight  = hardwareMap.get(DcMotor.class, "FR");
            frontLeft = hardwareMap.get(DcMotor.class, "FL");
            backRight = hardwareMap.get(DcMotor.class, "BR");
            backLeft = hardwareMap.get(DcMotor.class, "BL");
            finger = hardwareMap.get(Servo.class, "F");
            finger2 = hardwareMap.get(Servo.class, "F2");
            claw = hardwareMap.get(Servo.class, "C");

            frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            telemetry.addData("Status", "Initialized");

            //////////////////
            waitForStart();
            //////////////////

            // Keep claw from dragging on the ground
            claw.setPosition(1);

            //move forward
            frontRight.setPower(.85);
            frontLeft.setPower(-.85);
            backRight.setPower(.85);
            backLeft.setPower(-.85);
            sleep(850);

            //turn left
            frontRight.setPower(.85);
            frontLeft.setPower(.85);
            backRight.setPower(.85);
            backLeft.setPower(.85);
            sleep(600);

            //move backwards
            frontRight.setPower(-.85);
            frontLeft.setPower(.85);
            backRight.setPower(-.85);
            backLeft.setPower(.85);
            sleep(600);

            //move sideways
            frontRight.setPower(-.85);
            frontLeft.setPower(-.85);
            backRight.setPower(.85);
            backLeft.setPower(.85);
            sleep(350);

            //grab foundation (stops and grabs)
            frontRight.setPower(0);
            frontLeft.setPower(0);
            backRight.setPower(0);
            backLeft.setPower(0);
            sleep(1000);
            finger.setPosition(0.05);
            finger2.setPosition(0.35);
            sleep(500);
            frontRight.setPower(0);
            frontLeft.setPower(0);
            backRight.setPower(0);
            backLeft.setPower(0);
            sleep(1000);

            //put in building zone
            frontRight.setPower(.63);
            frontLeft.setPower(.63);
            backRight.setPower(-.85);
            backLeft.setPower(-.85);
            sleep(1450);
            frontRight.setPower(.85);
            frontLeft.setPower(.85);
            backRight.setPower(-.85);
            backLeft.setPower(-.85);
            sleep(1900);
            finger.setPosition(0.5);
            finger2.setPosition(0);

            //move forward to line
            frontRight.setPower(.85);
            frontLeft.setPower(-.85);
            backRight.setPower(.85);
            backLeft.setPower(-.85);
            sleep(1400);

            //Drop the robot off of the lander

        /*
            climb.setPower(-1);
            while(opModeIsActive() && climb.getCurrentPosition() < 20000){
                telemetry.addData("dropping", climb.getCurrentPosition());
                telemetry.update();
            }
            climb.setPower(0);

            telemetry.addData("value", left.getCurrentPosition());
            telemetry.update();
            sleep(1000);
            //Turn 180
            right.setPower(-1);
            left.setPower(1);
            while(opModeIsActive() && right.getCurrentPosition() < 3600){
                telemetry.addData("turning 180", right.getCurrentPosition());
                telemetry.update();
            }
            left.setPower(0);
            right.setPower(0);

            sleep(1000);

            //Drive into the middle mineral
            right.setPower(-0.9);
            left.setPower(-1);
            while(opModeIsActive() && right.getCurrentPosition() < 13000){
                telemetry.addData("Moving into the middle mineral", right.getCurrentPosition());
                telemetry.update();
            }
            left.setPower(0);
            right.setPower(0);

            //turn
            right.setPower(0.5);
            left.setPower(-1);
            while(opModeIsActive() && right.getCurrentPosition() > 11500){
                telemetry.addData("turning", right.getCurrentPosition());
                telemetry.update();
            }
            left.setPower(0);
            right.setPower(0);


            //drop marker
            dropper.setPosition(1);
            sleep(500);

            //go into enemy crater
            right.setPower(-0.95);
            left.setPower(-1);
            while(opModeIsActive() && right.getCurrentPosition() < 17000){
                telemetry.addData("going into enemy crater", right.getCurrentPosition());
                telemetry.update();
            }
            left.setPower(0);
            right.setPower(0);
            */


            telemetry.addData("done", "done");
            telemetry.update();

    }
}