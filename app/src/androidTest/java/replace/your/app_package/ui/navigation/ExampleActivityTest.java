package replace.your.app_package.ui.navigation;

import com.eaglesakura.android.garnet.Garnet;

import org.junit.Test;

import android.app.Activity;

import junit.framework.Assert;
import replace.your.app_package.AppScenarioTest;
import replace.your.app_package.provider.AppViewModelProvider;
import replace.your.app_package.ui.navigation.example.ExampleFragmentMain;
import replace.your.app_package.ui.viewmodel.example.ExampleAsyncDataViewModel;

import static com.eaglesakura.android.devicetest.scenario.ScenarioContext.assertTopActivity;
import static com.eaglesakura.android.devicetest.scenario.ScenarioContext.findViewById;
import static com.eaglesakura.android.devicetest.scenario.ScenarioContext.getFragment;
import static com.eaglesakura.android.devicetest.scenario.ScenarioContext.getTopActivity;
import static junit.framework.Assert.assertEquals;

public class ExampleActivityTest extends AppScenarioTest<ExampleActivity> {

    public ExampleActivityTest() {
        super(ExampleActivity.class);
    }

    @Test
    public void Activityが起動できる() throws Throwable {
        assertTopActivity(ExampleActivity.class);
    }

    @Test
    public void 同じActivityから同じViewModelが取得できる() throws Throwable {
        ExampleAsyncDataViewModel instance0 = Garnet.factory(AppViewModelProvider.class).depend(Activity.class, getTopActivity()).instance(ExampleAsyncDataViewModel.class);
        ExampleAsyncDataViewModel instance1 = Garnet.factory(AppViewModelProvider.class).depend(Activity.class, getTopActivity()).instance(ExampleAsyncDataViewModel.class);
        assertEquals(instance0, instance1);
    }
}