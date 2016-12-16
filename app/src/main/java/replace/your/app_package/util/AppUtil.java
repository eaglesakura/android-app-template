package replace.your.app_package.util;

import android.content.Context;

import replace.your.app_package.R;

/**
 *
 */
public class AppUtil {

    public static String getErrorTitle(Context context, Throwable e) {
        return context.getString(R.string.Title_Error_Runtime);
    }

    public static String getErrorMessage(Context context, Throwable e) {
        return context.getString(R.string.Message_Error_Runtime);
    }

    /**
     * FirebaseとGoogle APIログインを同時に行う場合のサンプルコード
     * これはAndriders Central Engineの場合
     */
//    public static GoogleApiClient.Builder newFullPermissionClient(Context context) {
//        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(context.getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        return new GoogleApiClient.Builder(context)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, options)
//                // Google Fit
//                .addApiIfAvailable(Fitness.HISTORY_API, Fitness.SCOPE_ACTIVITY_READ_WRITE, Fitness.SCOPE_BODY_READ_WRITE, Fitness.SCOPE_LOCATION_READ_WRITE)
//                .addApiIfAvailable(Fitness.BLE_API)
//                .addApiIfAvailable(Fitness.SESSIONS_API)
//                // GPS
//                .addApiIfAvailable(LocationServices.API)
//                ;
//    }
}
