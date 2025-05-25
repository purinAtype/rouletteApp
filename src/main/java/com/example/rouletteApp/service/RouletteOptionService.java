package com.example.rouletteApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.rouletteApp.form.OptionForm;
import com.example.rouletteApp.mapper.RouletteCategoryMapper;
import com.example.rouletteApp.mapper.RouletteOptionMapper;
import com.example.rouletteApp.model.RouletteCategory;
import com.example.rouletteApp.model.RouletteOption;
import com.example.rouletteApp.model.RouletteOptionExample;

@Service
public class RouletteOptionService {

    private final RouletteOptionMapper optionMapper;
    private final RouletteCategoryMapper categoryMapper;

    public RouletteOptionService(RouletteOptionMapper optionMapper,
                                  RouletteCategoryMapper categoryMapper) {
        this.optionMapper = optionMapper;
        this.categoryMapper = categoryMapper;
    }

    // 指定カテゴリIDに属する選択肢をすべて取得
    public List<RouletteOption> findByCategoryId(Integer categoryId) {
        RouletteOptionExample example = new RouletteOptionExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        return optionMapper.selectByExample(example);
    }

    // 新規登録
    public void insert(OptionForm form) {
        RouletteOption option = new RouletteOption();
        option.setCategoryId(form.getCategoryId());
        option.setOptionLabel(form.getOptionLabel());
        optionMapper.insertSelective(option);
    }

    // ID指定でフォーム用データを取得
    public OptionForm getFormById(Integer id) {
        RouletteOption option = optionMapper.selectByPrimaryKey(id);
        OptionForm form = new OptionForm();
        form.setId(option.getId());
        form.setCategoryId(option.getCategoryId());
        form.setOptionLabel(option.getOptionLabel());
        return form;
    }

    // 編集更新
    public void update(OptionForm form) {
        RouletteOption option = new RouletteOption();
        option.setId(form.getId());
        option.setOptionLabel(form.getOptionLabel());
        optionMapper.updateByPrimaryKeySelective(option);
    }

    // 削除し、削除された選択肢が属していたカテゴリIDを返す
    public Integer delete(Integer id) {
        RouletteOption option = optionMapper.selectByPrimaryKey(id);
        Integer categoryId = option.getCategoryId();
        optionMapper.deleteByPrimaryKey(id);
        return categoryId;
    }

    // カテゴリIDからイベントIDを取得（テンプレート戻り先などに使用）
    public Integer getEventIdByCategoryId(Integer categoryId) {
        RouletteCategory category = categoryMapper.selectByPrimaryKey(categoryId);
        return category != null ? category.getEventId() : null;
    }
}
