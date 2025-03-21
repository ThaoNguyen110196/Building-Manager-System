package com.building_mannager_system.controller.propertyController;


import com.building_mannager_system.dto.requestDto.propertyDto.ItemCheckDto;
import com.building_mannager_system.dto.requestDto.propertyDto.ItemCheckFlutterDto;
import com.building_mannager_system.dto.requestDto.propertyDto.ItemCheckWithResultsDto;
import com.building_mannager_system.dto.responseDto.ApiResponce;
import com.building_mannager_system.dto.responseDto.ResUserDTO;
import com.building_mannager_system.entity.property_manager.ItemCheck;
import com.building_mannager_system.service.property_manager.ItemCheckWithResultsService;
import com.building_mannager_system.service.system_service.ItemCheckService;
import com.building_mannager_system.utils.annotation.ApiMessage;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item-checks")
public class ItemCheckController {

    private final ItemCheckService itemCheckService;
    private final ItemCheckWithResultsService itemCheckWithResultsService;

    public ItemCheckController(ItemCheckService itemCheckService,
                               ItemCheckWithResultsService itemCheckWithResultsService) {

        this.itemCheckService = itemCheckService;
        this.itemCheckWithResultsService = itemCheckWithResultsService;

    }

    // **1. Lấy danh sách ItemCheck theo deviceId**
    @GetMapping("/device/{deviceId}")
    public ResponseEntity<Page<ItemCheckFlutterDto>> getItemChecksByDeviceId(
            @PathVariable Long deviceId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size) {

        Page<ItemCheckFlutterDto> itemChecks = itemCheckService.getAllItemChecksByDeviceId(deviceId, page, size);
        return ResponseEntity.ok(itemChecks);
    }

    // **2. Lấy chi tiết ItemCheck theo ID**
    @GetMapping("/web/{id}")
    public ResponseEntity<ItemCheckDto> getItemCheck(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(itemCheckService.getItemCheck(id));
    }

    @GetMapping("/with-results/{deviceId}")
    public ResponseEntity<List<ItemCheckWithResultsDto>> getItemChecksWithResults(
            @PathVariable Long deviceId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size) {

        List<ItemCheckWithResultsDto> resultList = itemCheckWithResultsService.getAllItemChecksWithResultsByDeviceId(deviceId, page, size);

        return ResponseEntity.ok(resultList);
    }
    // **3. Thêm mới ItemCheck**
    @PostMapping
    public ResponseEntity<ApiResponce<ItemCheckFlutterDto>> createItemCheck(@RequestBody ItemCheckFlutterDto itemCheck) {
        ItemCheckFlutterDto createdItemCheck = itemCheckService.createItemCheck(itemCheck);
        ApiResponce<ItemCheckFlutterDto> responce = new ApiResponce(200,createdItemCheck,"success");

        return ResponseEntity.ok(responce);
    }

    // **4. Cập nhật ItemCheck**
    @PutMapping("/{id}")
    public ResponseEntity<ItemCheck> updateItemCheck(@PathVariable Long id, @RequestBody ItemCheckFlutterDto updatedItemCheck) {
        ItemCheck itemCheck = itemCheckService.updateItemCheck(id, updatedItemCheck);
        return ResponseEntity.ok(itemCheck);
    }

    // **5. Xóa ItemCheck**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemCheck(@PathVariable Long id) {
        itemCheckService.deleteItemCheck(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponce<ItemCheckFlutterDto>> getItemCheckById(@PathVariable Long id) {
        ItemCheckFlutterDto itemCheck = itemCheckService.getItemCheckById(id);
        ApiResponce<ItemCheckFlutterDto> responce = new ApiResponce(200,itemCheck,"success");
        return ResponseEntity.ok(responce);
    }

}