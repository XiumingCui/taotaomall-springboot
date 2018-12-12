package app.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PageController {



    @RequestMapping(value = "/manager/{page}")
    public ModelAndView page(@PathVariable String page){
        return new ModelAndView("manager/"+page);
    }


}
