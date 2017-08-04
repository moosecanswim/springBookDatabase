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


        sendModel.addAttribute("listOfBooks",bookList);
        return "showbooks";
    }

    @GetMapping("/adddefaultbook")
    public String addDefaultBook()
    {

        //bookDB.add(new Book("sku","title","author","description",100));
        String defaultBooks[][] ={
                {
                        "Java1001", "Head First Java", "Kathy Sierra and Bert Bates", "Easy to read Java workbook", "47.50"
                },
                {
                        "Java1002", "Thinking in Java", "Bruce Eckel", "Details about Java under the hood", "20.00"
                },
                {
                        "Orcl1003", "OCP: Oracle Certified Professional Java SE", "Jeanne Boyarsky", "Everything you need to know in one place", "45.00"
                },
                {
                        "Python1004", "Automate the Boring Stuff with Python", "Al Sweigart", "Fun with Python", "10.50"
                },
                {
                        "Zombie1005", "The Maker's Guide to the Zombie Apocalypse", "Simon Monk", "Defend Your Base with Simple Circuits, Arduino, and Raspberry Pi", "16.50"
                },
                {
                        "Rasp1006", "Raspberry Pi Projects for the Evil Genius", "Kyle", "A dozen fiendishly fun projects for the Raspberry Pi!", "14.75"
                },
                {
                        "Rasp1007", "Rasb 2", "Kyle", "A dozen fiendishly fun projects for the Raspberry Pi!", "15.75"
                }

        };


        for(int i = 0; i < defaultBooks.length ;i++){
            Book defaultBook = new Book();
            defaultBook.setBookSku(defaultBooks[i][0]);
            defaultBook.setBookTitle(defaultBooks[i][1]);
            defaultBook.setBookAuthor(defaultBooks[i][2]);
            defaultBook.setBookDescription(defaultBooks[i][3]);
            String tempPrice = defaultBooks[i][4];
            double tempBookPrice = Double.valueOf(tempPrice);
            defaultBook.setBookPrice(tempBookPrice);
            bookRepository.save(defaultBook);
         }
            return "showbooks";
    }

}

