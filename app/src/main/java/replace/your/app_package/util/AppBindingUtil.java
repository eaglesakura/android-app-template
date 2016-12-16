package replace.your.app_package.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * FIXME: DataBindingに特殊なバインドを行う場合はこれを参考にする
 */
public class AppBindingUtil {
    @BindingAdapter("srcCompat")
    public static void appSrcCompat(ImageView imageView, Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }
}
