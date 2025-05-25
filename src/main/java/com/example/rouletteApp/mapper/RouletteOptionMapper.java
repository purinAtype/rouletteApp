package com.example.rouletteApp.mapper;

import com.example.rouletteApp.model.RouletteOption;
import com.example.rouletteApp.model.RouletteOptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RouletteOptionMapper {
    long countByExample(RouletteOptionExample example);

    int deleteByExample(RouletteOptionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RouletteOption row);

    int insertSelective(RouletteOption row);

    List<RouletteOption> selectByExample(RouletteOptionExample example);

    RouletteOption selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") RouletteOption row, @Param("example") RouletteOptionExample example);

    int updateByExample(@Param("row") RouletteOption row, @Param("example") RouletteOptionExample example);

    int updateByPrimaryKeySelective(RouletteOption row);

    int updateByPrimaryKey(RouletteOption row);
}