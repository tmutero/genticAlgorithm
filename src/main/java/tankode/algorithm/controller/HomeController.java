package tankode.algorithm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    public static final String DATASETS_PATH = "datasets/";
    String[] fileNames;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

        List<String> results = new ArrayList<String>();
        File[] files = new File(DATASETS_PATH).listFiles();
//If this pathname does not denote a directory, then listFiles() returns null.

        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        model.addAttribute("files", results);
        return "index";
    }








}
