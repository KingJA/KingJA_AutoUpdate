package lib.king.kupdate.task;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import lib.king.kupdate.Util;
import lib.king.kupdate.dialog.DialogDouble;
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
    private String updateContent;

    public VersionTask(Activity context, boolean updateCancleable,boolean showDownloadDialog,LoadStrategy loadStrategy,String updateContent) {
        this.context = context;
        this.cancleable = updateCancleable;
        this.showDownloadDialog = showDownloadDialog;
        this.loadStrategy = loadStrategy;
        this.updateContent = updateContent;
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

        DialogDouble dialogDouble = new DialogDouble(context, updateContent, "下次", "马上升级");
        dialogDouble.setOnDoubleClickListener(new DialogDouble.OnDoubleClickListener() {
            @Override
            public void onLeft() {
                System.exit(0);
            }

            @Override
            public void onRight() {
                new DownloadTask(context,showDownloadDialog).execute();
            }
        });
        dialogDouble.setCanceledOnTouchOutside(cancleable);
        dialogDouble.setCancelable(cancleable);
        dialogDouble.show();

//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("检测到版本更新");
//        builder.setMessage(updateContent);
//        builder.setPositiveButton("马上更新", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//             new DownloadTask(context,showDownloadDialog).execute();
//            }
//        });
//        if (cancleable) {
//            builder.setNegativeButton("下次", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                    System.exit(0);
//                }
//            });
//        }
//        Dialog dialog = builder.create();
//        dialog.setCanceledOnTouchOutside(cancleable);
//        dialog.setCancelable(cancleable);
//        dialog.show();
    }

}
