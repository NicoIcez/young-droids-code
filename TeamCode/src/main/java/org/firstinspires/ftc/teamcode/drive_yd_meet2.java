package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

//chloe l. 12/11/18
@TeleOp
public class drive_yd_meet2 extends LinearOpMode{
    //create motor, sensor, servo objects, etc
    private DcMotor lifterArm;
    private DcMotor leftDrive;
    private DcMotor rightDrive;


    @Override
    public void runOpMode(){
        //set motor, etc objects to rev
        lifterArm = hardwareMap.get(DcMotor.class, "lifterArm");
        //right and left motors from the robot's POV
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");

        //leftDrive.setDirection(DcMotorSimple.Direction.REVERSE); this is how to reverse motor's direction

        double lefty = 0;
        double leftx = 0;
        double righty = 0;
        double rightx = 0;


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //delays then steps forward once play is pressed
        waitForStart();
        while(opModeIsActive()){

            lefty = this.gamepad1.left_stick_y;
            leftx = this.gamepad1.left_stick_x;
            righty = this.gamepad1.right_stick_y;
            rightx = this.gamepad1.right_stick_x;


            telemetry.addData("Status ", "Running");
            telemetry.addData("lifter arm power ", righty);
            telemetry.addData("right motor power ", 0);
            telemetry.addData("left motor power ", 0);
            telemetry.update();

            //left joystick: forward = drive forward: backwards = back
            drive(leftx, lefty);
            //pull-up
            lifterArm.setPower(righty);
        }
    }

    public void drive(double leftstickx, double leftsticky){
        //have to take the 2 meshed gears in account; 1 turns motor clockwise
        //turn if xpos > ypos
        if(Math.abs(leftstickx) > Math.abs(leftsticky)){
            leftDrive.setPower(leftstickx);
            rightDrive.setPower(leftstickx);

        } else {
            rightDrive.setPower(-leftsticky);
            leftDrive.setPower(leftsticky);
        }
    }
}
