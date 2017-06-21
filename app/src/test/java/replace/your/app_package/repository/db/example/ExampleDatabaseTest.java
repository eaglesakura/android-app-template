package replace.your.app_package.repository.db.example;

import org.junit.Test;

import android.arch.persistence.room.Room;

import java.io.Closeable;
import java.util.List;

import io.reactivex.Flowable;
import replace.your.app_package.AppUnitTestCase;

public class ExampleDatabaseTest extends AppUnitTestCase {

    @Test
    public void Databaseインスタンスを生成できる() throws Throwable {
        ExampleDatabase database = Room.databaseBuilder(getContext(), ExampleDatabase.class, "example.db")
                .allowMainThreadQueries()
                .build();

        assertNotNull(database);

        ExampleDatabase.DataAccess dao = database.getDao();
        assertNotNull(dao);

        try (Closeable token = database.open()) {
            database.runInTx(() -> {
                {
                    ExampleDatabase.User user = new ExampleDatabase.User();
                    user.name = "user1";
                    long[] keys = dao.insert(user);

                    assertEquals(keys.length, 1);
                    assertEquals(keys[0], 1);

                    ExampleDatabase.ToDo todo = new ExampleDatabase.ToDo();
                    todo.title = "example";
                    todo.userId = keys[0];

                    dao.insert(todo);
                }
                {
                    ExampleDatabase.User user = new ExampleDatabase.User();
                    user.name = "user2";
                    long[] keys = dao.insert(user);

                    assertEquals(keys.length, 1);
                    assertEquals(keys[0], 2);
                }
                return 0;
            });
        }

        try (Closeable token = database.open()) {
            List<ExampleDatabase.TodoCard> todoCards = dao.listTodoCards();
            assertEquals(todoCards.size(), 1);
            ExampleDatabase.TodoCard card = todoCards.get(0);

            assertEquals(card.title, "example");
            assertEquals(card.NAME, "user1");
        }


        try (Closeable token = database.open()) {
            Flowable<ExampleDatabase.TodoCard> todoCards = dao.listTodoCardsEx();
            todoCards.subscribe(card -> {
                assertEquals(card.title, "example");
                assertEquals(card.NAME, "user1");
            });
        }
    }
}