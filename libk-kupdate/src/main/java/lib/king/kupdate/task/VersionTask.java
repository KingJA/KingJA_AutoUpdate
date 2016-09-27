package lib.king.kupdate.task;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import lib.king.kupdate.Util;
import lib.king.kupdate.strategy.LoadStrategy;

/**
 * Description：TODO
 * Create Time：2016/9/26 13:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class VersionTask extends AsyncTask<String, Integer, Integer> {
    private Activity context;
    private boolean cancleable;
    private boolean showDownloadDialog;
    private LoadStrategy loadStrategy;

    public VersionTask(Activity context, boolean updateCancleable,boolean showDownloadDialog,LoadStrategy loadStrategy) {
        this.context = context;
        this.cancleable = updateCancleable;
        this.showDownloadDialog = showDownloadDialog;
        this.loadStrategy = loadStrategy;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(String... params) {
        return loadStrategy.getVersionCode(params[0]);
    }

    @Override
    protected void onPostExecute(Integer i) {
        super.onPostExecute(i);
        if (Util.ifNeedUpdate(i, context)) {
            Log.e("onPostExecute", "需要更新: ");
            showAskDialog();
        }
    }

    private void showAskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("软件版本更新");
        builder.setMessage("检测到新版本，是否立即进行更新?");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
             new DownloadTask(context,showDownloadDialog).execute();
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
        dialog.setCanceledOnTouchOutside(cancleable);
        dialog.setCancelable(cancleable);
        dialog.show();
    }

}
