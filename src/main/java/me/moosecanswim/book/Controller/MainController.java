package me.moosecanswim.book.Controller;

import me.moosecanswim.book.Model.Book;
import me.moosecanswim.book.bookRepository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/")
    public String index(Model model)
    {
        String sendToIndex = "This is my WELCOME page (from controler)";
        model.addAttribute("message", sendToIndex);
        return "index";
    }
    @GetMapping("/addbook")
    public String addABook(Model model){
        model.addAttribute("newBook", new Book());
        return "addbook";
    }

    @PostMapping("/addbook")
    public String addABook(@Valid @ModelAttribute("newBook") Book newBook, BindingResult bindingResult)
    {
        System.out.println("**"+bindingResult.toString());
        if(bindingResult.hasErrors()){
            return "addbook";
        }
        //add the book to the database
        System.out.println("*adding a new book");
        System.out.println(newBook.getBookSku());
        bookRepository.save(newBook);
        return "showbookdetails";
    }
    @GetMapping("/showbooks")
    //public @ResponseBody String listProducts(Model sendModel)
    public  String listProducts(Model sendModel)
    {

        Iterable <Book> bookList = bookRepository.findAll();

        for(Book i: bookList) {
            Long thisID = i.getID();
            String thisSKU = i.getBookSku();
            String thisTitle = i.getBookTitle();
            String thisDesc = i.getBookDescription();
            double thisPrice = i.getBookPrice();

            String output = String.format("| sku: %s | title: %s | description: %s | price: $%.2f",thisSKU,thisTitle,thisDesc,thisPrice);
            System.out.println(output);
        }
        sendModel.addAttribute("listOfBooks",bookList);
        return "showbooks";
    }







}
