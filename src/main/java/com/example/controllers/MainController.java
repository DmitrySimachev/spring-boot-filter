package com.example.controllers;

import com.example.models.Tags;
import com.example.repo.TagRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private TagRepository tagRepository;

    public MainController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping("/receive")
    public List<Tags> getData(HttpServletRequest request) {

        String str = request.getParameter("str");
        System.err.println("Запрос от пользователя " + str);

        List<Tags> tags = tagRepository.findAll(str);

        System.err.println("Фильтрованный список " + tags);

        return tags;
    }

    @ResponseBody
    @ResponseStatus
    @RequestMapping("/save")
    public String saveData(@RequestBody File jsonFile) {

        System.err.println("Сработал");

        ObjectMapper objectMapper = new ObjectMapper();
        List<Tags> tags = null;
        try {
            tags = objectMapper.readValue(jsonFile, new TypeReference<List<Tags>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (tags != null) {
            System.err.println("Нужно записать " + tags.toString());
            tagRepository.saveAll(tags);
        }

        return "";
    }

    public static JSONObject getSocksJSON(String tag) {
        JSONObject json = new JSONObject();
        try {
            json.put("tag", tag);
        } catch (JSONException ignored) { }

        return json;
    }
}
