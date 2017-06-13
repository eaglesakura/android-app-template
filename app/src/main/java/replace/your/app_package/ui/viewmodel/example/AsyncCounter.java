package replace.your.app_package.ui.viewmodel.example;

import com.eaglesakura.sloth.app.lifecycle.ServiceLifecycle;
import com.eaglesakura.sloth.app.lifecycle.SlothLiveData;

/**
 * 非同期実行している時間を保存するLiveData
 */
public class AsyncCounter extends SlothLiveData<Integer> {
    ServiceLifecycle mLifecycle;

    @Override
    protected void onActive() {
        super.onActive();
        mLifecycle = new ServiceLifecycle();
        mLifecycle.onCreate();
        mLifecycle.asyncQueue(task -> {
            for (int i = 0; i < 10; ++i) {
                task.waitTime(1000);
                Integer value = getValue();
                if (value == null) {
                    value = 0;
                }
                task.throwIfCanceled();
                postValue(value + 1);
            }
            return this;
        }).start();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        mLifecycle.onDestroy();
        mLifecycle = null;
    }

    void increment() {
        Integer value = getValue();
        if (value == null) {
            postValue(0);
        } else {
            postValue(value + 1);
        }
    }
}
