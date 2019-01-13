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
import java.util.List;
import java.util.Set;

public class BooksController extends Controller {

    @Inject
    FormFactory formFactory;

    private Book getBookFromForm(Form<Book> bookForm) {
        String id = bookForm.field("id").getValue().orElse("not present");
        String title = bookForm.field("title").getValue().orElse("not present");
        String price = bookForm.field("price").getValue().orElse("not present");
        String author = bookForm.field("author").getValue().orElse("not present");
        return new Book(Integer.parseInt(id), title, Integer.parseInt(price), author);
    }

    // Show all books
    public Result index() {
        List<Book> books = Book.find.all();
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
        book.save();
        return redirect(routes.BooksController.index());
    }

    // Edit book
    public Result edit(Integer id) {
        Book book = Book.find.byId(id);
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
        Book oldBook = Book.find.byId(book.id);
        if (oldBook == null) {
            return notFound("Book not found");
        }
        if(!oldBook.id.equals(book.id)){
            oldBook.id = book.id;
        }
        if(!oldBook.author.equals(book.author)){
            oldBook.author = book.author;
        }
        if(!oldBook.title.equals(book.title)){
            oldBook.title = book.title;
        }
        if(!oldBook.price.equals(book.price)){
            oldBook.price = book.price;
        }
        oldBook.save();
        return redirect(routes.BooksController.index());
    }

    // Delete book
    public Result destroy(Integer id) {
        Book book = Book.find.byId(id);
        if (book == null) {
            return notFound("Book not found");
        }
        book.delete();
        return redirect(routes.BooksController.index());
    }

    // Display single book
    public Result show(Integer id) {
        Book book = Book.find.byId(id);
        if (book == null) {
            return notFound("Book not found");
        }
        return ok(show.render(book));
    }
}
