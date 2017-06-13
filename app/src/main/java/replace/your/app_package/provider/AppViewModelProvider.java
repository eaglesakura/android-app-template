package replace.your.app_package.provider;

import com.eaglesakura.android.garnet.Provide;
import com.eaglesakura.sloth.provider.GarnetViewModelProvider;

import android.arch.lifecycle.ViewModelProviders;

import replace.your.app_package.ui.viewmodel.example.ExampleAsyncDataViewModel;
import replace.your.app_package.ui.viewmodel.image.AppImageViewModel;

/**
 * ViewModelの依存解決を行う
 *
 * ViewModelごとに、依存対象となるFragmentは異なる。
 *
 * 基本的にActivity/Fragment単位でライフサイクルを保持するが、Singletonや毎度生成する等、オブジェクトの特性ごとに使い分ける。
 */
public class AppViewModelProvider extends GarnetViewModelProvider {

    @Provide
    public AppImageViewModel provideImageViewModel() {
        return getViewModelFromActivity(AppImageViewModel.class);
    }

    @Provide
    public ExampleAsyncDataViewModel provideExampleAsyncDataViewModel() {
        return getViewModelFromActivity(ExampleAsyncDataViewModel.class);
    }
}
