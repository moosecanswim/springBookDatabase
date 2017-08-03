package me.moosecanswim.book.Controller;

import me.moosecanswim.book.Model.Book;
import me.moosecanswim.book.bookRepository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
        bookRepository.save(newBook);
        return "showbookdetails";
    }







}
