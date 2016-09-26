package lib.king.kupdate;

import android.app.Activity;
import android.content.Context;

/**
 * Description：TODO
 * Create Time：2016/9/26 13:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class UpdateManager {
    private boolean cancleable;
    private Activity activity;

    public UpdateManager(Builder builder) {
        this.cancleable = builder.cancleable;
        this.activity = activity;
    }

    public void checkUpdate() {
        UpdateAsyncTask updateAsyncTask = new UpdateAsyncTask(activity,cancleable);
        updateAsyncTask.execute(Constants.APK_NAME);
    }

    public static class Builder {
        private Activity activity;
        private boolean cancleable;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder setCancleable(boolean cancleable) {
            this.cancleable = cancleable;
            return this;
        }

        public UpdateManager build() {
            return new UpdateManager(this);
        }
    }
}
