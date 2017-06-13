package replace.your.app_package.ui.viewmodel.example;

import com.eaglesakura.cerberus.CallbackTime;
import com.eaglesakura.cerberus.ExecuteTarget;
import com.eaglesakura.sloth.app.lifecycle.GarnetViewModel;

/**
 * サンプルデータのViewModel
 *
 * 非同期処理と組み合わせている。
 */
public class ExampleAsyncDataViewModel extends GarnetViewModel {

    AsyncCounter mAsyncCounter;

    public ExampleAsyncDataViewModel() {
    }

    @Override
    protected void onInitialize() {
        mAsyncCounter = new AsyncCounter();

        registerLiveData(mAsyncCounter);
    }

    @Override
    protected void onActive() {
        super.onActive();
        getLifecycle().async(ExecuteTarget.LocalQueue, CallbackTime.CurrentForeground, task -> {
            for (int i = 0; i < 5; ++i) {
                task.waitTime(1000);
                task.throwIfCanceled();
                mAsyncCounter.increment();
            }
            return this;
        });
    }

    @Override
    protected void onInactive() {
        super.onInactive();
    }

    public AsyncCounter getAsyncCounter() {
        return mAsyncCounter;
    }
}
