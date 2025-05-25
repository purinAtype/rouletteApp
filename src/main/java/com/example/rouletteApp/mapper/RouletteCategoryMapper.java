package com.example.rouletteApp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.rouletteApp.model.RouletteCategory;
import com.example.rouletteApp.model.RouletteCategoryExample;

@Mapper
public interface RouletteCategoryMapper {
    long countByExample(RouletteCategoryExample example);

    int deleteByExample(RouletteCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RouletteCategory row);

    int insertSelective(RouletteCategory row);

    List<RouletteCategory> selectByExample(RouletteCategoryExample example);

    RouletteCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") RouletteCategory row, @Param("example") RouletteCategoryExample example);

    int updateByExample(@Param("row") RouletteCategory row, @Param("example") RouletteCategoryExample example);

    int updateByPrimaryKeySelective(RouletteCategory row);

    int updateByPrimaryKey(RouletteCategory row);
}