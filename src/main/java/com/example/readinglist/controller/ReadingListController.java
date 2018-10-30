package com.example.readinglist.controller;

import com.example.readinglist.condition.JdbcTemplateCondition;
import com.example.readinglist.entity.Book;
import com.example.readinglist.reposity.ReadingListReposity;
import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@ConditionalOnBean(JdbcTemplateCondition.class)
public class ReadingListController {

    private ReadingListReposity reposity;

    public ReadingListController(ReadingListReposity reposity) {
        this.reposity = reposity;
    }

    @GetMapping("/{reader}")
    public String readersBooks(@PathVariable("reader") String reader, Model model) {
        List<Book> list = reposity.findByReader(reader);
        if (list != null) {
            model.addAttribute("books", list);
        }
        return "readingList";
    }

    @PostMapping("/{reader}")
    public String addToReadingList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        reposity.save(book);
        return "redirect:/{reader}";
    }


}
