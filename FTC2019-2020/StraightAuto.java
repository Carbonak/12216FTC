package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="StraightAuto", group="Linear Opmode")
public class StraightAuto extends LinearOpMode {
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

        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status", "Initialized");

        //////////////////
        waitForStart();
        //////////////////

        //move to line
        frontRight.setPower(.85);
        frontLeft.setPower(-.85);
        backRight.setPower(.85);
        backLeft.setPower(-.85);
        sleep(1000);

        telemetry.addData("done", "done");
        telemetry.update();

    }
}
