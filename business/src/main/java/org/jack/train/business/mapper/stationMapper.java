package org.jack.train.business.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jack.train.business.domain.station;
import org.jack.train.business.domain.stationExample;

public interface stationMapper {
    long countByExample(stationExample example);

    int deleteByExample(stationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(station record);

    int insertSelective(station record);

    List<station> selectByExample(stationExample example);

    station selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") station record, @Param("example") stationExample example);

    int updateByExample(@Param("record") station record, @Param("example") stationExample example);

    int updateByPrimaryKeySelective(station record);

    int updateByPrimaryKey(station record);
}