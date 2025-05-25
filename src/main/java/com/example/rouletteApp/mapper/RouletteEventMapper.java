package com.example.rouletteApp.mapper;

import com.example.rouletteApp.model.RouletteEvent;
import com.example.rouletteApp.model.RouletteEventExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RouletteEventMapper {
    long countByExample(RouletteEventExample example);

    int deleteByExample(RouletteEventExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RouletteEvent row);

    int insertSelective(RouletteEvent row);

    List<RouletteEvent> selectByExample(RouletteEventExample example);

    RouletteEvent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") RouletteEvent row, @Param("example") RouletteEventExample example);

    int updateByExample(@Param("row") RouletteEvent row, @Param("example") RouletteEventExample example);

    int updateByPrimaryKeySelective(RouletteEvent row);

    int updateByPrimaryKey(RouletteEvent row);
}