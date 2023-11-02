package com.shop.sport.Controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.shop.sport.Entity.User;
import com.shop.sport.Service.FileUpload;
import com.shop.sport.Utils.PushNoti.FirebaseMessageService;
import com.shop.sport.Utils.PushNoti.Notification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "Tutorial", description = "Tutorial management APIs")
@RestController
@RequestMapping("/api/v1/test")
public class tesstcontroller {

    @Autowired
    private FirebaseMessageService firebaseService;

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("helo nho nam");
    }

    @Operation(
            summary = "Retrieve a Tutorial by Id",
            description = "Get a Tutorial object by specifying its id. The response is Tutorial object with id, title, description and published status.",
            tags = { "tutorials", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/tutorials/{id}")
    public ResponseEntity<String> getTutorialById(@PathVariable("id") long id) {
        return ResponseEntity.ok("helo nho nam");

    }


    @RequestMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody Notification note,
                                   @RequestParam String token) throws FirebaseMessagingException {
        return firebaseService.sendNotification(note, token);
    }


}
