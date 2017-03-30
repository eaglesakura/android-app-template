package replace.your.app_package;

import com.eaglesakura.sloth.Sloth;

import org.junit.Test;

public class BuildConfigTest extends AppDeviceTestCase {

    @Test
    public void 正しいApplicationContextを得ることができる() {
        assertTrue(getApplication() instanceof CustomApplication);
        assertTrue(Sloth.getApplication() instanceof CustomApplication);
    }

}
