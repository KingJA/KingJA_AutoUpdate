package lib.king.kupdate;

/**
 * Description：TODO
 * Create Time：2016/9/26 13:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class UpdateManager {
    private UpdateManager() {

    }
    private volatile static UpdateManager mUpdateManager;

    public static UpdateManager getInstance() {
        if (mUpdateManager == null) {
            synchronized (UpdateManager.class) {
                if (mUpdateManager == null) {
                    mUpdateManager=new UpdateManager();
                }
            }
        }
        return mUpdateManager;
    }

    public void checkUpdate() {

    }
}
