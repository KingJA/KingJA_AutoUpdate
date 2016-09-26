package lib.king.kupdate;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Description：TODO
 * Create Time：2016/9/26 13:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class UpdateAsyncTask extends AsyncTask<String, Integer, Integer> {
    private Activity context;
    private boolean cancleable;

    public UpdateAsyncTask(Activity context,boolean cancleable) {
        this.context = context;
        this.cancleable = cancleable;
    }

    /**
     * execute调用时首先执行
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * 后台任务
     * @param params
     * @return
     */
    @Override
    protected Integer doInBackground(String... params) {
        return UpdateService.getVersionCode(params[0]);
    }

    /**
     * doInBackground执行完调用
     * @param i doInBackground的返回值
     */
    @Override
    protected void onPostExecute(Integer i) {
        super.onPostExecute(i);
        if (UpdateUtil.ifNeedUpdate(i, context)) {
            Log.e("onPostExecute", "需要更新: ");
            showAskDialog();
        }
    }

    /**
     * 显示下载对话框
     */
    private void showAskDialog() {
        //构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("软件版本更新");
        builder.setMessage("检测到新版本，是否立即进行更新?");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(context,"进入后台更新",Toast.LENGTH_SHORT).show();
              DownloadService.goService(context);
            }
        });
        if (cancleable) {
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
