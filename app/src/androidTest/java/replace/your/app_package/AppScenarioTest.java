package replace.your.app_package;

import com.eaglesakura.sloth.ui.support.SupportActivity;
import com.eaglesakura.util.Util;

import org.junit.Rule;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

/**
 * シナリオテスト用
 */
public abstract class AppScenarioTest<ActivityClass extends SupportActivity> extends AppDeviceTestCase {

    @Rule
    public final ActivityTestRule<ActivityClass> mRule;

    public AppScenarioTest(Class<ActivityClass> clazz) {
        mRule = new ActivityTestRule<>(clazz);
    }

    @Override
    public void onSetup() {
        super.onSetup();
        autoBootActivity();
    }

    protected void autoBootActivity() {
        mRule.launchActivity(new Intent());
        Util.sleep(1000);
    }
}
