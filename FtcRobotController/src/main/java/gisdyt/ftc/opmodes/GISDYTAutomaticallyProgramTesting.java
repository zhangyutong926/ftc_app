package gisdyt.ftc.opmodes;

import android.widget.Toast;

import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import gisdyt.ftc.utils.GISDYTAutomaticallyProgramUtil;

/**
 * Created by Administrator on 2015/11/19.
 */
public class GISDYTAutomaticallyProgramTesting extends OpMode {

    private DcMotor motor1, motor2, motor3, motor4;
//    private GISDYTAutomaticallyProgramUtil autoUtil;
    private long lastActionTime;

    @Override
    public void init() {
//        Toast.makeText(FtcRobotControllerActivity.activity, "GISDYTAutomaticallyProgramTesting OpMode Loaded, Toast Testing...", Toast.LENGTH_LONG).show();

        motor1=hardwareMap.dcMotor.get("m1");
        motor2=hardwareMap.dcMotor.get("m2");
        motor3=hardwareMap.dcMotor.get("m3");
        motor4=hardwareMap.dcMotor.get("m4");
        motor1.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor2.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor3.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor4.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

//        autoUtil=new GISDYTAutomaticallyProgramUtil();
//        autoUtil.registerStatement(new GISDYTAutomaticallyProgramUtil.AutomaticallyStatementStart() {
//            @Override
//            public void start() {
//                Toast.makeText(FtcRobotControllerActivity.activity, "GISDYTAutomaticallyTesting Auto Period Start", Toast.LENGTH_SHORT).show();
//                motor1.setPower(1.0);
//                motor2.setPower(1.0);
//                motor3.setPower(1.0);
//                motor4.setPower(1.0);
//            }
//        }, new GISDYTAutomaticallyProgramUtil.AutomaticallyStatementStop() {
//            @Override
//            public void stop() {
//                Toast.makeText(FtcRobotControllerActivity.activity, "GISDYTAutomaticallyTesting Auto Period Stop", Toast.LENGTH_SHORT).show();
//                motor1.setPower(0.0);
//                motor2.setPower(0.0);
//                motor3.setPower(0.0);
//                motor4.setPower(0.0);
//            }
//        }, 5000);

        lastActionTime=System.currentTimeMillis();
    }

    private int state=0;
    private final long[] periodGap={5000, 5000, 5000};

    @Override
    public void loop() {
        switch(state){
            case 0:
                telemetry.addData("State", state+" case0");
                motor1.setPower(1.0);
                motor2.setPower(1.0);
                motor3.setPower(1.0);
                motor4.setPower(1.0);
                break;
            case 1:
                telemetry.addData("State", state+" case1");
                motor1.setPower(-1.0);
                motor2.setPower(-1.0);
                motor3.setPower(-1.0);
                motor4.setPower(-1.0);
                break;
            default:
                telemetry.addData("State", state+" case default");
                motor1.setPower(0);
                motor2.setPower(0);
                motor3.setPower(0);
                motor4.setPower(0);
                return;
        }
        if(System.currentTimeMillis()-lastActionTime>=periodGap[state]){
            lastActionTime=System.currentTimeMillis();
            state++;
        }
        //四个马达同时正转5秒
//        motor1.setPower(1.0);
//        motor2.setPower(1.0);
//        motor3.setPower(1.0);
//        motor4.setPower(1.0);
//        if(System.currentTimeMillis()-lastActionTime<=5000) return ;
//        //四个马达反转5秒
//        autoUtil.loop();
    }
}
