package replace.your.app_package.ui.navigation;

import org.junit.Test;

import replace.your.app_package.AppScenarioTest;

import static com.eaglesakura.android.devicetest.scenario.ScenarioContext.assertTopActivity;

public class ExampleActivityTest extends AppScenarioTest<ExampleActivity> {

    public ExampleActivityTest() {
        super(ExampleActivity.class);
    }

    @Test
    public void Activityが起動できる() throws Throwable {
        assertTopActivity(ExampleActivity.class);
    }
}