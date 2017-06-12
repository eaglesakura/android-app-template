package replace.your.app_package.provider;

import replace.your.app_package.repository.AppSettings;

public class TestAppContextProvider extends AppContextProvider {
    @Override
    public AppSettings provideAppSettings() {
        AppSettings settings = new AppSettings.Builder(getContext())
                .store(AppSettings.newDatabasePropertyStore(getContext()))
                .build();
        return settings;
    }
}
