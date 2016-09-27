package lib.king.kupdate;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

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

    public static int getVersionCode( Context context) {
        return getAppInfo(context).versionCode;
    }

    public static boolean ifNeedUpdate(int versionCode,Context context) {
        return versionCode>getVersionCode(context);
    }

}
