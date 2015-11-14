package gisdyt.ftc.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Administrator on 2015/11/12.
 */
public class GISDYTTest1 extends OpMode{

    private DcMotor motor1, motor2, motor3, motor4;

    @Override
    public void init() {
        telemetry.addData("Program:", "init@GISDYTTest1");
        telemetry.addData("Init Progress Status 1:", "Starting...");
        motor1=hardwareMap.dcMotor.get("motor1");
        motor2=hardwareMap.dcMotor.get("motor2");
        motor3=hardwareMap.dcMotor.get("motor3");
        motor4=hardwareMap.dcMotor.get("motor4");
        telemetry.addData("Init Progress Status 2:", "Finished...");
        telemetry.addData("About:", "This program was write by GISDYT, thanks for your using.");
        motor1.setPower(0.5545626149126756426124612546215);
        telemetry.addData("motor1:", motor1.getPower());
        motor1.setPower(0);
    }

    @Override
    public void loop() {
        telemetry.addData("Program:", "loop@GISDYTTest1");
        float forw=gamepad1.left_stick_y;
        float rota=-gamepad1.right_stick_x;
        motor1.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motor2.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motor3.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motor4.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
//        if(hori>0) forward((int) hori*10);
//        else if(hori<0) backward((int) hori*10);
//        if(vert > 0) left((int) vert*10);
//        else if(vert < 0) right((int) vert*10);
//        motor1.setPower(left);
//        motor2.setPower(left);
//        motor3.setPower(-right);
//        motor4.setPower(-right);
        double l=0, r=0;
        l=forw+rota;
        r=-(forw-rota);
        if(l<-1.0) l=-1.0;
        else if(l>1.0) l=1.0;
        if(r<-1.0) r=-1.0;
        else if(r>1.0) r=1.0;
        motor1.setPower(l);
        motor2.setPower(l);
        motor3.setPower(r);
        motor4.setPower(r);
        telemetry.addData("Motor1 running in power:", motor1.getPower());
        telemetry.addData("Motor2 running in power:", motor2.getPower());
        telemetry.addData("Motor3 running in power:", motor3.getPower());
        telemetry.addData("Motor4 running in power:", motor4.getPower());
        telemetry.addData("About:", "This program was write by GISDYT, thanks for your using.");
    }
}
