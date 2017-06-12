package replace.your.app_package.ui.viewmodel.example;

import com.eaglesakura.sloth.app.lifecycle.SlothLiveData;
import com.eaglesakura.util.Timer;

/**
 * 非同期実行している時間を保存するLiveData
 */
public class AsyncTimeData extends SlothLiveData<Integer> {
    @Override
    protected void onActive() {
        super.onActive();
        getLifecycle().asyncQueue(task -> {
            Timer timer = new Timer();
            for (int i = 0; i < 10; ++i) {
                task.waitTime(1000);
                postValue((int) timer.end());
            }
            return this;
        }).start();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
    }
}
