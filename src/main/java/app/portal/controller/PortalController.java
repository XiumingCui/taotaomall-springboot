package app.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PortalController {

    @RequestMapping(value = "/portal/{page}")
    public ModelAndView page(@PathVariable String page){
        return new ModelAndView("portal/"+page);
    }

    @RequestMapping(value = "/commons/{page}")
    public ModelAndView commonsPage(@PathVariable String page){
        return new ModelAndView("portal/commons/"+page);
    }


}
