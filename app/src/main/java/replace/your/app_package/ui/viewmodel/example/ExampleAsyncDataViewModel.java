package replace.your.app_package.ui.viewmodel.example;

import android.arch.lifecycle.ViewModel;

/**
 * サンプルデータのViewModel
 *
 * 非同期処理と組み合わせている。
 */
public class ExampleAsyncDataViewModel extends ViewModel {

    AsyncTimeData mAsyncTimeData;

    public ExampleAsyncDataViewModel() {
    }

    public AsyncTimeData getAsyncTimeData() {
        if (mAsyncTimeData == null) {
            mAsyncTimeData = new AsyncTimeData();
        }
        return mAsyncTimeData;
    }
}
