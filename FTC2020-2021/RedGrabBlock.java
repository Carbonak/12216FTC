package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


@Autonomous(name="RedGrabBlock", group="Linear Opmode")
public class RedGrabBlock extends LinearOpMode
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
    private BNO055IMU imu = null;
    private Orientation lastAngles = new Orientation();
    private double globalAngle, correction;


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

            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

            parameters.mode = BNO055IMU.SensorMode.IMU;
            parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
            parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parameters.loggingEnabled = false;

            imu = hardwareMap.get(BNO055IMU.class, "imu");

            imu.initialize(parameters);

            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            telemetry.addData("Status", "Initialized");


            //////////////////
            waitForStart();
            //////////////////

            /*
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setTargetPosition(1440);
            while(motor.isBusy() && opModeIsActive()) {
                //Loop body can be empty
            }
            motor.setPower(0);
            */


            // Keep claw from dragging on the ground
            claw.setPosition(1);

            //move forward
            frontRight.setPower(.85);
            frontLeft.setPower(-.85);
            backRight.setPower(.85);
            backLeft.setPower(-.85);

            while (opModeIsActive() && frontLeft.getCurrentPosition() < -2000){
                sleep(1);
            }

            sleep(10000);

            telemetry.addData("done", "done");
            telemetry.update();

    }
}