package com.example.minibooking.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/landlord-api")
@RequiredArgsConstructor
@Tag(name = "Landlord API", description = "Возможности арендодателя")
public class LandLordApiController {


}
