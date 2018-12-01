package tankode.algorithm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tankode.algorithm.algorithm.Evolution;

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


        long budget = 100000000;
        float minCoverage = 0.9f;
        // The number of individuals in a generation. You may change this parameter
        // if you need to. However, if you choose to change it - leave a comment why.
        int generationSize = 1000;
        Evolution evolution = new Evolution(generationSize, budget, minCoverage);

        //stop after "terminated signal or after n iterations (default: 1000)
        while (evolution.isTerminated() && evolution.getGenerationNumber() < 1000) {
            evolution.evolve();
        }
        return "index";
    }








}
