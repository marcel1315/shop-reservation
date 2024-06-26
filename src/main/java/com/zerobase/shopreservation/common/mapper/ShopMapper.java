package com.zerobase.shopreservation.common.mapper;

import com.zerobase.shopreservation.customer.dto.OneShopSearchDto;
import com.zerobase.shopreservation.customer.dto.ShopOutputDto;
import com.zerobase.shopreservation.customer.dto.ShopSearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopMapper {
    long selectListCount(ShopSearchDto shopSearchDto);

    List<ShopOutputDto> selectList(ShopSearchDto shopSearchDto);

    ShopOutputDto selectOne(OneShopSearchDto oneShopSearchDto);
}