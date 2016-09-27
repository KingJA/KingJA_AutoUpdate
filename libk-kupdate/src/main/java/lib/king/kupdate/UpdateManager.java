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
    private boolean showDownloadDialog;
    private Activity activity;

    public UpdateManager(Builder builder) {
        this.showDownloadDialog = builder.showDownloadDialog;
        this.cancleable = builder.cancleable;
        this.activity = builder.activity;
    }

    public void checkUpdate() {
        VersionTask versionTask = new VersionTask(activity,cancleable,showDownloadDialog);
        versionTask.execute(Constants.APK_NAME);
    }

    public static class Builder {
        private Activity activity;
        private boolean cancleable;
        private boolean showDownloadDialog;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder setCancleable(boolean cancleable) {
            this.cancleable = cancleable;
            return this;
        }
        public Builder setShowDownloadDialog(boolean showDownloadDialog) {
            this.showDownloadDialog = showDownloadDialog;
            return this;
        }

        public UpdateManager build() {
            return new UpdateManager(this);
        }
    }
}
