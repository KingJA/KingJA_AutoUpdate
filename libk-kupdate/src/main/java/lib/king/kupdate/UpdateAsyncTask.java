package lib.king.kupdate;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Description：TODO
 * Create Time：2016/9/26 13:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class UpdateAsyncTask extends AsyncTask<String, Integer, Integer> {
    private Activity context;

    public UpdateAsyncTask(Activity context) {
        this.context = context;
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
            Intent intent = new Intent(context, DownloadService.class);
            context.startService(intent);
        }
    }
}
