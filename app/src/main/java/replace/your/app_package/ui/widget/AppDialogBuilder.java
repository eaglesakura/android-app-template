package replace.your.app_package.ui.widget;

import com.eaglesakura.sloth.view.builder.DialogBuilder;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import replace.your.app_package.util.AppUtil;

/**
 * アプリ用のDialog生成
 */
public class AppDialogBuilder extends DialogBuilder {
    public AppDialogBuilder(AlertDialog.Builder builder) {
        super(builder);
    }

    public static AppDialogBuilder newError(Context context, Throwable error) {
        AppDialogBuilder builder = new AppDialogBuilder(new AlertDialog.Builder(context));
        builder.mBuilder.setTitle(AppUtil.getErrorTitle(context, error));
        builder.mBuilder.setMessage(AppUtil.getErrorMessage(context, error));
        return builder;
    }
}
