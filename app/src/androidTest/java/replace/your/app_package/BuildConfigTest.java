package replace.your.app_package;

import com.eaglesakura.android.framework.FrameworkCentral;

import org.junit.Test;

public class BuildConfigTest extends AppDeviceTestCase {

    @Test
    public void 正しいApplicationContextを得ることができる() {
        assertTrue(getApplication() instanceof CustomApplication);
        assertTrue(FrameworkCentral.getApplication() instanceof CustomApplication);
    }

}
