package com.kob.backend.controller.pk;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/pk/")
public class BotInfoController {

    @RequestMapping("getBotInfo/")
    public List<String> getBotInfo() {
        List<String> list = new LinkedList<>();
        list.add("老师");
        list.add("laoshi");

        return list;
    }
}
