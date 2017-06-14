package replace.your.app_package.provider.data;

import com.eaglesakura.android.garnet.Provide;
import com.eaglesakura.sloth.provider.ContextProvider;
import com.eaglesakura.thread.LazyObjectHolder1;

import android.arch.persistence.room.Room;
import android.content.Context;

import replace.your.app_package.repository.db.example.ExampleDatabase;

/**
 * DBの依存管理を行う
 */
public class AppDatabaseProvider extends ContextProvider {

    /**
     * 読み取り専用オブジェクト
     */
    static LazyObjectHolder1<ExampleDatabase, Context> sExampleDatabase = new LazyObjectHolder1<>(context -> {
        return Room.databaseBuilder(context, ExampleDatabase.class, "example.db")
                .build();
    });

    @Override
    public void onDependsCompleted(Object inject) {
    }

    /**
     * サンプルデータを開く
     */
    @Provide
    public ExampleDatabase provideExampleDatabase() {
        return sExampleDatabase.get(getContext());
    }

    @Override
    public void onInjectCompleted(Object inject) {
    }
}
