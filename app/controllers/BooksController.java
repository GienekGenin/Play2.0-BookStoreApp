package controllers;

import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.Books.index;
import views.html.Books.create;

import javax.inject.Inject;
import java.util.Set;

public class BooksController extends Controller {

    @Inject
    FormFactory formFactory;

    // Show all books
    public Result index() {
        Set<Book> books = Book.allBooks();
        return ok(index.render(books));
    }

    // Create form for book
    public Result create() {
        Form<Book> bookForm = formFactory.form(Book.class);
        return ok(create.render(bookForm));
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
