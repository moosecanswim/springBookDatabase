package me.moosecanswim.book.bookRepository;

import me.moosecanswim.book.Model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Long> {

}
