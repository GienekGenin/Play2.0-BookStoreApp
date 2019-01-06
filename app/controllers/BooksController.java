package controllers;

import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.Books.index;
import views.html.Books.create;
import views.html.Books.edit;
import views.html.Books.show;

import javax.inject.Inject;
import java.util.Set;

public class BooksController extends Controller {

    @Inject
    FormFactory formFactory;

    private Book getBookFromForm(Form<Book> bookForm){
        String id = bookForm.field("id").getValue().orElse("not present");
        String title = bookForm.field("title").getValue().orElse("not present");
        String price = bookForm.field("price").getValue().orElse("not present");
        String author = bookForm.field("author").getValue().orElse("not present");
        return new Book(Integer.parseInt(id), title, Integer.parseInt(price), author);
    }

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
    // TODO: find better way for getting values from form
    public Result save() {
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        Book book = getBookFromForm(bookForm);
        Book.add(book);
        return redirect(routes.BooksController.index());
    }

    // Edit book
    public Result edit(Integer id) {
        Book book = Book.findById(id);
        if (book == null) {
            return notFound("Book not found");
        }
        // TODO: .fill(book) not working
        Form<Book> bookForm = formFactory.form(Book.class);
        return ok(edit.render(bookForm, book));
    }

    // Update book
    public Result update() {
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        Book book = getBookFromForm(bookForm);
        Book oldBook = Book.findById(book.id);
        if(oldBook == null){
            return notFound("Book not found");
        }
        if(!oldBook.equals(book)){
            oldBook.id = book.id;
            oldBook.title = book.title;
            oldBook.author = book.author;
            oldBook.price = book.price;
        }
        return redirect(routes.BooksController.index());
    }

    // Delete book
    public Result destroy(Integer id) {
        Book book = Book.findById(id);
        if(book == null){
            return notFound("Book not found");
        }
        Book.remove(book);
        return redirect(routes.BooksController.index());
    }

    // Display single book
    public Result show(Integer id) {
        Book book = Book.findById(id);
        if(book == null){
            return notFound("Book not found");
        }
        return ok(show.render(book));
    }
}
