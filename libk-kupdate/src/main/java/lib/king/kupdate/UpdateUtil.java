package lib.king.kupdate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * 项目名称：物联网城市防控(警用版)
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/4/1 14:56
 * 修改备注：
 */
public class UpdateUtil {

    private static PackageInfo getAppInfo(Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static String getVersionName(Context context) {
        return getAppInfo( context).versionName;
    }

    public static int getVersionCode( Context context) {
        return getAppInfo(context).versionCode;
    }

    public static boolean ifNeedUpdate(int versionCode,Context context) {
        Log.e("UpdateUtil", "versionCode: "+versionCode+"currentCode: "+getVersionCode(context));
        return versionCode>getVersionCode(context);
    }

    public static void showDialog(Context context,boolean cancleAble) {
        //构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("软件版本更新");
        builder.setMessage("检测到新版本，是否立即进行更新?");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                showDownLoadDialog();
            }
        });
        if (cancleAble) {
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    System.exit(0);
                }
            });
        }

        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);//dialog弹出后会点击屏幕，dialog不消失；点击物理返回键dialog消失
        dialog.setCancelable(false);//dialog弹出后会点击屏幕或物理返回键，dialog不消失
        dialog.show();
    }

}
