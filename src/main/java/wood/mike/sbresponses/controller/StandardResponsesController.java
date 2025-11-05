package wood.mike.sbresponses.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class StandardResponsesController {

    @GetMapping("/a")
    @ResponseBody           // need to use this in a non-RestController
    public String a() {
        return "<h2>a</h2>";
    }

    @GetMapping("/b")
    public Model b(Model model) {
        model.addAttribute("id", "b-bbbb");
        return model;
    }

    @GetMapping("/c")
    public String c(Model model) {
        model.addAttribute("id", "c-cccc");
        return "c";
    }

    @GetMapping("/d")
    public ModelAndView d(ModelAndView modelAndView) {
        modelAndView.addObject("id", "d-dddd");
        modelAndView.setViewName("d");
        return modelAndView;
    }

    @ModelAttribute("messages")
    public List<String> messages() {
        return Arrays.asList("a", "b", "c", "d");
    }
}
