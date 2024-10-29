package api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Book;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.List;

public class BookApiClient {
    private static final String API_URL = "https://gutendex.com/books/";

    public List<Book> getBooks() throws IOException {
        String jsonResponse = fetchBooks();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(jsonResponse, objectMapper.getTypeFactory().constructCollectionType(List.class, Book.class));
        } catch (IOException e) {
            System.err.println("Erro ao converter JSON para lista de livros: " + e.getMessage());
            throw e;
        }
    }

    private String fetchBooks() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(API_URL).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        }
    }
}
