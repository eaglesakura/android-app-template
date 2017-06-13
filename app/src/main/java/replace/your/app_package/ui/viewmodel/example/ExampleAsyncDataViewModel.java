package replace.your.app_package.ui.viewmodel.example;

import com.eaglesakura.sloth.app.lifecycle.GarnetViewModel;

/**
 * サンプルデータのViewModel
 *
 * 非同期処理と組み合わせている。
 */
public class ExampleAsyncDataViewModel extends GarnetViewModel {

    AsyncTimeData mAsyncTimeData;

    public ExampleAsyncDataViewModel() {
    }

    @Override
    protected void onInitialize() {
        mAsyncTimeData = new AsyncTimeData();

        registerLiveData(mAsyncTimeData);
    }

    public AsyncTimeData getAsyncTimeData() {
        return mAsyncTimeData;
    }
}
