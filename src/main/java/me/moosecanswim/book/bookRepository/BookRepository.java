package me.moosecanswim.book.bookRepository;

import me.moosecanswim.book.Model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Long> {
    /***
     * to find based on an sku
     * Iterable <product> findAllByDescriptionLike(String partialString);
     */


}
