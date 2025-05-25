package com.example.rouletteApp.form;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EventForm {

    @NotBlank(message = "イベント名を入力してください")
    private String eventName;
}
