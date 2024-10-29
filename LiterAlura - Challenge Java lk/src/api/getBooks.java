package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import model.Book;
import java.io.IOException;
import java.util.List;

public class BookApiClient {
    private static final String API_URL = "https://gutendex.com/books/";

    public List<Book> getBooks() throws IOException {
        String jsonResponse = fetchBooks();
        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
        String booksArray = jsonObject.get("results").toString();

        return gson.fromJson(booksArray, new TypeToken<List<Book>>() {}.getType());
    }
}
