package com.example.rouletteApp.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CategoryForm {

    private Integer id;

    @NotNull(message = "イベントIDは必須です")
    private Integer eventId;

    @NotBlank(message = "カテゴリ名を入力してください")
    private String categoryName;
}
