package com.example.rouletteApp.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OptionForm {

    private Integer id;

    @NotNull(message = "カテゴリIDは必須です")
    private Integer categoryId;

    @NotBlank(message = "候補名を入力してください")
    private String optionLabel;
}
