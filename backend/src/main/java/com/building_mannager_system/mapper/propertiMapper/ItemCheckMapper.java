package com.building_mannager_system.mapper.propertiMapper;

import com.building_mannager_system.dto.requestDto.propertyDto.ItemCheckFlutterDto;
import com.building_mannager_system.entity.property_manager.ItemCheck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemCheckMapper {
    // Khởi tạo instance của Mapper
    ItemCheckMapper INSTANCE = Mappers.getMapper(ItemCheckMapper.class);

    // Map từ DTO sang Entity
    @Mapping(target = "id", ignore = true) // Bỏ qua trường ID khi thêm mới
    @Mapping(target = "device.deviceId",source = "deviceId")

    ItemCheck toEntity(ItemCheckFlutterDto dto);

    // Map từ Entity sang DTO
    @Mapping(source = "device.deviceId",target = "deviceId")

    ItemCheckFlutterDto toDto(ItemCheck entity);
}