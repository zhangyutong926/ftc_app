package gisdyt.ftc.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2015/11/14.
 */
public class GISDYTRoboticOpUtil {

    public static void confMotors(List<DcMotor> motor_list, DcMotorController.RunMode mode){
        Iterator<DcMotor> iter=motor_list.iterator();
        while(iter.hasNext()){
            DcMotor temp=iter.next();
            temp.setMode(mode);
        }
    }

    public static void joystickArcade2(float ward_axis, float rotate_axis, DcMotor motor_left, DcMotor motor_right, boolean inv_left, boolean inv_right){
        double l=ward_axis+rotate_axis;
        double r=-(ward_axis-rotate_axis);
        if(l<-1.0) l=-1.0;
        else if(l>1.0) l=1.0;
        if(r<-1.0) r=-1.0;
        else if(r>1.0) r=1.0;
        if(!inv_left) motor_left.setPower(l);
        else motor_left.setPower(-l);
        if(!inv_right) motor_right.setPower(-r);
        else motor_left.setPower(r);
    }

    public static void joystickArcade4(float ward_axis, float rotate_axis, DcMotor motor_left1, DcMotor motor_left2, DcMotor motor_right1, DcMotor motor_right2, boolean inv_left1, boolean inv_left2, boolean inv_right1, boolean inv_right2){
        double l=ward_axis+rotate_axis;
        double r=-(ward_axis-rotate_axis);
        if(l<-1.0) l=-1.0;
        else if(l>1.0) l=1.0;
        if(r<-1.0) r=-1.0;
        else if(r>1.0) r=1.0;
        if(!inv_left1) motor_left1.setPower(l);
        else motor_left1.setPower(-l);
        if(!inv_left2) motor_left2.setPower(l);
        else motor_left2.setPower(-l);
        if(!inv_right1) motor_right1.setPower(-r);
        else motor_left1.setPower(r);
        if(!inv_right2) motor_right2.setPower(-r);
        else motor_left2.setPower(r);
    }

    public static void joystickTank2(float left_axis, float right_axis, DcMotor motor_left, DcMotor motor_right, boolean inv_left, boolean inv_right){
        if(!inv_left) motor_left.setPower(left_axis);
        else motor_left.setPower(-left_axis);
        if(!inv_right) motor_right.setPower(-right_axis);
        else motor_right.setPower(right_axis);
    }

    public static void joystickTank4(float left_axis, float right_axis, DcMotor motor_left1, DcMotor motor_left2, DcMotor motor_right1, DcMotor motor_right2, boolean inv_left1, boolean inv_left2, boolean inv_right1, boolean inv_right2){
        if(inv_left1) motor_left1.setPower(left_axis);
        else motor_left1.setPower(-left_axis);
        if(inv_left2) motor_left2.setPower(left_axis);
        else motor_left2.setPower(-left_axis);
        if(inv_right1) motor_right1.setPower(-right_axis);
        else motor_right1.setPower(right_axis);
        if(inv_right2) motor_right2.setPower(-right_axis);
        else motor_right2.setPower(right_axis);
    }
}
