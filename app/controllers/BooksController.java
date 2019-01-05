package controllers;

import models.Book;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.Books.index;

import java.util.Set;

public class BooksController extends Controller {

    // Show all books
    public Result index() {
        Set<Book> books = Book.allBooks();
        return ok(index.render(books));
    }

    // Create book
    public Result create() {
        return TODO;
    }

    // Save book
    public Result save() {
        return TODO;
    }

    // Edit book
    public Result edit(Integer id) {
        return TODO;
    }

    // Update book
    public Result update() {
        return TODO;
    }

    // Delete book
    public Result destroy(Integer id) {
        return TODO;
    }

    // Display single book
    public Result show(Integer id){
        return TODO;
    }
}
