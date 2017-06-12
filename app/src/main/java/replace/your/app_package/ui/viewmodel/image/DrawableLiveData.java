package replace.your.app_package.ui.viewmodel.image;

import com.eaglesakura.cerberus.BackgroundTask;
import com.eaglesakura.cerberus.CallbackTime;
import com.eaglesakura.cerberus.ExecuteTarget;
import com.eaglesakura.lambda.CancelCallback;
import com.eaglesakura.sloth.app.lifecycle.SlothLiveData;
import com.eaglesakura.sloth.data.SupportCancelCallbackBuilder;
import com.eaglesakura.sloth.graphics.SyncImageLoader;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import java.io.IOException;

import replace.your.app_package.repository.AppImageRepository;
import replace.your.app_package.util.AppLog;

/**
 * 1Drawableごとに管理されるLiveData
 *
 * キャッシュは {@link AppImageViewModel} で共用される。
 */
public class DrawableLiveData extends SlothLiveData<Drawable> {

    @DrawableRes
    int mErrorDrawableId;

    /**
     * 画像のローカルキャッシュを持つ場合はtrue
     * デフォルトはtrue
     */
    boolean mImageCache;

    /**
     * キャンセルチェック
     */
    CancelCallback mCancelCallback;

    /**
     * ロード対象のURI
     */
    @NonNull
    Uri mUri;

    /**
     * ローダー本体
     */
    AppImageRepository mImageRepository;

    DrawableLiveData(AppImageRepository repository, Uri uri) {
        mUri = uri;
        mImageRepository = repository;
    }

    /**
     * エラー時のDrawableを指定する
     */
    public DrawableLiveData setErrorDrawableId(@DrawableRes int errorDrawableId) {
        mErrorDrawableId = errorDrawableId;
        return this;
    }

    /**
     * 画像のキャッシュを持たないならtrue
     */
    public DrawableLiveData ignoreImageCache() {
        mImageCache = true;
        return this;
    }

    /**
     * キャンセルコールバックをデフォルト以外で指定する
     */
    public DrawableLiveData setCancelCallback(CancelCallback cancelCallback) {
        mCancelCallback = cancelCallback;
        return this;
    }

    protected Drawable loadImage(SupportCancelCallbackBuilder cancelCallbackBuilder) throws IOException {
        if (mCancelCallback != null) {
            cancelCallbackBuilder.or(mCancelCallback);
        }

        SyncImageLoader.Builder builder = mImageRepository.newImage(mUri, mImageCache);
        if (mErrorDrawableId != 0) {
            builder.errorImage(mErrorDrawableId, true);
        }

        return builder.await(cancelCallbackBuilder.build());
    }

    @Override
    protected void onActive() {
        super.onActive();

        getLifecycle().async(ExecuteTarget.LocalParallel, CallbackTime.Alive, (BackgroundTask<Drawable> task) -> {
            return loadImage(SupportCancelCallbackBuilder.from(task));
        }).completed((result, task) -> {
            setValue(result);
        }).failed((error, task) -> {
            AppLog.printStackTrace(error);
        }).start();
    }
}
