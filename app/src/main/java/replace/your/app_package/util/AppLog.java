package replace.your.app_package.util;

import com.eaglesakura.android.garnet.Garnet;
import com.eaglesakura.android.garnet.Inject;
import com.eaglesakura.util.LogUtil;

import android.content.Context;

import java.lang.reflect.Method;

import replace.your.app_package.provider.LoggerProvider;

/**
 * アプリ用のログ出力をラップする
 *
 * FIXME: 必要に応じてログを一括ON/OFFできるようにする。
 */
public class AppLog {

    @Inject(value = LoggerProvider.class, name = LoggerProvider.NAME_DEFAULT)
    static LogUtil.Logger sDefaultLogger;

    @Inject(value = LoggerProvider.class, name = LoggerProvider.NAME_APPLOG)
    static LogUtil.Logger sAppLogger;

    static Class FirebaseCrash;

    static Method FIrebaseCrash_report;

    public static void printStackTrace(Throwable e) {
        e.printStackTrace();
    }

    /**
     * send firebase report
     *
     * FIXME: Firebaseを導入するならばレポートを送信する。
     */
    public static void report(Throwable e) {
        e.printStackTrace();
        try {
            if (FirebaseCrash == null) {
                FirebaseCrash = Class.forName("com.google.firebase.crash.FirebaseCrash");
                FIrebaseCrash_report = FirebaseCrash.getDeclaredMethod("report", Throwable.class);
            }
            FIrebaseCrash_report.invoke(FirebaseCrash);
        } catch (Throwable fbc) {
            fbc.printStackTrace();
        }
    }

    /**
     * 再度ロガーを注入する
     */
    public static void inject(Context context) {
        Garnet.create(AppLog.class)
                .depend(Context.class, context)
                .inject();
        LogUtil.setLogger(sDefaultLogger);
    }

    public static void widget(String fmt, Object... args) {
        String tag = "App.Widget";

        LogUtil.setLogger(tag, sAppLogger);
        LogUtil.out(tag, fmt, args);
    }

    public static void system(String fmt, Object... args) {
        String tag = "App.System";

        LogUtil.setLogger(tag, sAppLogger);
        LogUtil.out(tag, fmt, args);
    }

    public static void db(String fmt, Object... args) {
        String tag = "App.DB";

        LogUtil.setLogger(tag, sAppLogger);
        LogUtil.out(tag, fmt, args);
    }

    public static void gps(String fmt, Object... args) {
        String tag = "App.GPS";

        LogUtil.setLogger(tag, sAppLogger);
        LogUtil.out(tag, fmt, args);
    }

    public static void broadcast(String fmt, Object... args) {
        String tag = "App.Broadcast";

        LogUtil.setLogger(tag, sAppLogger);
        LogUtil.out(tag, fmt, args);
    }

    public static void bluetooth(String fmt, Object... args) {
        String tag = "App.Ble";

        LogUtil.setLogger(tag, sAppLogger);
        LogUtil.out(tag, fmt, args);
    }

    public static void test(String fmt, Object... args) {
        String tag = "App.Test";

        LogUtil.setLogger(tag, sAppLogger);
        LogUtil.out(tag, fmt, args);
    }
}
