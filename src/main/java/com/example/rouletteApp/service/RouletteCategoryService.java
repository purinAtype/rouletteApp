package com.example.rouletteApp.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.rouletteApp.form.CategoryForm;
import com.example.rouletteApp.mapper.RouletteCategoryMapper;
import com.example.rouletteApp.mapper.RouletteOptionMapper;
import com.example.rouletteApp.model.RouletteCategory;
import com.example.rouletteApp.model.RouletteCategoryExample;
import com.example.rouletteApp.model.RouletteOption;
import com.example.rouletteApp.model.RouletteOptionExample;

@Service
public class RouletteCategoryService {

	private final RouletteCategoryMapper categoryMapper;
    private final RouletteOptionMapper optionMapper;

    public RouletteCategoryService(RouletteCategoryMapper categoryMapper,
                                   RouletteOptionMapper optionMapper) {
        this.categoryMapper = categoryMapper;
        this.optionMapper = optionMapper;
    }
    
    // 指定カテゴリIDに紐づく情報を取得
    public RouletteCategory findById(Integer categoryId) {
        return categoryMapper.selectByPrimaryKey(categoryId);
    }

    public List<RouletteCategory> findByEventId(Integer eventId) {
        RouletteCategoryExample example = new RouletteCategoryExample();
        example.createCriteria().andEventIdEqualTo(eventId);
        return categoryMapper.selectByExample(example);
    }

    public void insert(CategoryForm form) {
        RouletteCategory entity = new RouletteCategory();
        entity.setEventId(form.getEventId());
        entity.setCategoryName(form.getCategoryName());
        categoryMapper.insertSelective(entity);
    }

    public CategoryForm getFormById(Integer id) {
        RouletteCategory cat = categoryMapper.selectByPrimaryKey(id);
        CategoryForm form = new CategoryForm();
        form.setId(cat.getId());
        form.setEventId(cat.getEventId());
        form.setCategoryName(cat.getCategoryName());
        return form;
    }

    public void update(CategoryForm form) {
        RouletteCategory entity = new RouletteCategory();
        entity.setId(form.getId());
        entity.setCategoryName(form.getCategoryName());
        categoryMapper.updateByPrimaryKeySelective(entity);
    }

    public Integer delete(Integer id) {
        RouletteCategory cat = categoryMapper.selectByPrimaryKey(id);
        categoryMapper.deleteByPrimaryKey(id);
        return cat.getEventId();
    }
    
    public String spinRoulette(Integer categoryId) {
        // 候補を取得
        RouletteOptionExample example = new RouletteOptionExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        List<RouletteOption> options = optionMapper.selectByExample(example);

        if (options == null || options.isEmpty()) {
            return "選択肢がありません";
        }

        // ランダム選択
        Random rand = new Random();
        int index = rand.nextInt(options.size());
        String result = options.get(index).getOptionLabel();

        // 結果をカテゴリに保存
        RouletteCategory category = new RouletteCategory();
        category.setId(categoryId);
        category.setResult(result);
        categoryMapper.updateByPrimaryKeySelective(category);

        return result;
    }

    public Integer getEventIdByCategoryId(Integer categoryId) {
        RouletteCategory category = categoryMapper.selectByPrimaryKey(categoryId);
        return category != null ? category.getEventId() : null;
    }
}
