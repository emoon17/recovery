package com.example.wehago.predict.mapper;

import com.example.wehago.predict.dto.PredictionRiskEntity;
import com.example.wehago.predict.dto.PredictionTargetDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PredictionMapper {
    List<PredictionTargetDto> selectPredictionTargets();
    void insertRiskPrediction(PredictionRiskEntity entity);
}
