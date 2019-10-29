package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import static java.lang.Thread.sleep;


@Autonomous(name="Encoder Auto", group="Linear Opmode")
public class Encoder_Autonomous extends LinearOpMode
{

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontRight = null;
    private DcMotor frontLeft = null;
    private DcMotor backRight = null;
    private DcMotor backLeft = null;
    /*
    private DcMotor right = null;
    private DcMotor left = null;
    private DcMotor climb = null;
    private Servo dropper = null;
    */


    @Override
        public void runOpMode() {

            telemetry.addData("Status", "Initialized");

            frontRight  = hardwareMap.get(DcMotor.class, "FR");
            frontLeft = hardwareMap.get(DcMotor.class, "FL");
            backRight = hardwareMap.get(DcMotor.class, "BR");
            backLeft = hardwareMap.get(DcMotor.class, "BL");

            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            telemetry.addData("Status", "Initialized");

            //////////////////
            waitForStart();
            //////////////////

            sleep(1000);
            //turn 90 degrees
            frontRight.setPower(1);
            frontLeft.setPower(-1);
            backRight.setPower(1);
            backLeft.setPower(-1);
            sleep(3000);
            frontRight.setPower(0);
            frontLeft.setPower(0);
            backRight.setPower(0);
            backLeft.setPower(0);
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