package replace.your.app_package.provider;

import com.eaglesakura.log.Logger;

public class TestLogProvider extends LoggerProvider {
    @Override
    public Logger.Impl provideAppLogger() {
        return new Logger.RobolectricLogger();
    }
}
