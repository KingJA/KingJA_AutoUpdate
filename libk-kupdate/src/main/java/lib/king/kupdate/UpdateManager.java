package lib.king.kupdate;

import android.app.Activity;

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
        this.activity = builder.activity;
    }

    public void checkUpdate() {
        VersionTask versionTask = new VersionTask(activity,cancleable);
        versionTask.execute(Constants.APK_NAME);
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
