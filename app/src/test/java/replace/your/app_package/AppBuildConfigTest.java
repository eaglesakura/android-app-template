package replace.your.app_package;

import com.eaglesakura.thread.Holder;

import org.junit.Test;

public class AppBuildConfigTest extends AppUnitTestCase {
    @Test
    public void 正しいClassがロードできている() throws Exception {
        assertNotNull(getContext());
        assertNotNull(getContext() instanceof CustomApplication);
        assertNotNull(getContext().getString(R.string.EsMaterial_Word_Common_DataLoad));
        assertEquals(BuildConfig.DEBUG, BuildConfig.APPLICATION_ID.endsWith(".debug"));
    }

    @Test
    public void ラムダ式を呼び出す() throws Exception {
        Holder<Boolean> holder = new Holder<>();
        holder.set(Boolean.FALSE);

        Runnable call = () -> {
            holder.set(Boolean.TRUE);
        };

        call.run();

        assertEquals(holder.get(), Boolean.TRUE);
    }
}