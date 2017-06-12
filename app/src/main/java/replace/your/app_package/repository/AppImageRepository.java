package replace.your.app_package.repository;

import com.eaglesakura.alternet.Alternet;
import com.eaglesakura.alternet.request.ConnectRequest;
import com.eaglesakura.alternet.request.SimpleHttpRequest;
import com.eaglesakura.sloth.graphics.SyncImageLoader;
import com.eaglesakura.util.Timer;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

/**
 * アプリ内で使用する画像管理を行う
 */
public class AppImageRepository extends SyncImageLoader {

    final private Alternet mNetworkConnector;

    public AppImageRepository(Context context) {
        this(context, 5, 5);
    }

    public AppImageRepository(Context context, @IntRange(from = 1) int imageCacheNum, @IntRange(from = 1) int errorCacheNum) {
        super(context, imageCacheNum, errorCacheNum);
        mNetworkConnector = new Alternet(context);
    }

    public Builder newImage(Uri uri, boolean onMemoryCache) {
        if (uri.toString().startsWith("http")) {
            // HTTP経由
            SimpleHttpRequest request = new SimpleHttpRequest(ConnectRequest.Method.GET);
            request.getCachePolicy().setCacheLimitTimeMs(Timer.toMilliSec(1, 0, 0, 0, 0));
            request.getCachePolicy().setMaxItemBytes(1024 * 512);
            request.setUrl(uri.toString(), null);
            return super.newImage(mNetworkConnector, request, onMemoryCache);
        } else {
            // OnMemory経由
            return super.newImage(uri, onMemoryCache);
        }
    }
}
