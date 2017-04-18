package com.ernest.reefangel.ws;

import com.ernest.reefangel.slack.SlackFileUploadService;
import com.ernest.reefangel.slack.SlackPushService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by ernest on 2017/04/18.
 */

@RestController
@RequestMapping(path = "/slack")
public class SlackController {

    private Logger log;
    private SlackPushService slackPushService;
    private SlackFileUploadService slackFileUploadService;

    @Autowired
    public SlackController(SlackPushService slackPushService, SlackFileUploadService slackFileUploadService) {
        this.slackPushService = slackPushService;
        this.slackFileUploadService = slackFileUploadService;
        this.log = Logger.getLogger(SlackController.class);
    }


    @RequestMapping(path = "/send/{message}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    @ResponseStatus(code = HttpStatus.OK)
    public void send(@PathVariable String message) throws InterruptedException, IOException {
        slackPushService.send(message);
    }


    @RequestMapping(path = "/upload", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    @ResponseStatus(code = HttpStatus.OK)
    public void upload() throws InterruptedException, IOException {
        slackFileUploadService.sendFile();
    }
}
