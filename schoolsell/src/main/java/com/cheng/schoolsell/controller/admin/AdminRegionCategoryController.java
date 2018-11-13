package com.cheng.schoolsell.controller.admin;

import com.cheng.schoolsell.entity.RegionCategory;
import com.cheng.schoolsell.enums.AdminResultEnum;
import com.cheng.schoolsell.exception.AdminException;
import com.cheng.schoolsell.form.AdminRegionCategoryForm;
import com.cheng.schoolsell.service.RegionCategoryService;
import com.cheng.schoolsell.utils.AdminResultMapUtil;
import com.cheng.schoolsell.utils.KeyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-20
 * Time: 上午7:56
 */
@Controller
@Slf4j
@RequestMapping("/admin/region")
@Api(tags = "管理员端区域划分管理")
public class AdminRegionCategoryController {

    @Autowired
    private RegionCategoryService regionCategoryService;

    @GetMapping("/list")
    @ApiOperation(value = "查看区域列表")
    public ModelAndView regionList(@RequestParam("token") String token,
                                   Map<String,Object> map) {

        List<RegionCategory> regionCategoryList = regionCategoryService.findAll();

        map.put("list", regionCategoryList);
        map.put("token", token);
        return new ModelAndView("admin/region/list",map);
    }

    @GetMapping("/save")
    @ApiOperation(value = "跳转到保存区域列表页")
    @CacheEvict(cacheNames = "region",allEntries = true)
    public ModelAndView regionSave(@RequestParam("token") String token,
                                   @RequestParam(value = "regionId",required = false) String regionId,
                                   Map<String, Object> map) {


        if (regionId != null) {
            Optional<RegionCategory> regionCategory = regionCategoryService.findOne(regionId);
            if (!regionCategory.isPresent()) {
                throw new AdminException(AdminResultEnum.ADMIN_REGION_EMPTY,token);
            }
            map.put("regionCategory", regionCategory.get());
        }
        map.put("token", token);
        return new ModelAndView("admin/region/save", map);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存、修改区域划分信息")
    @CacheEvict(cacheNames = "region",allEntries = true,beforeInvocation = true)
    public ModelAndView save(@Valid AdminRegionCategoryForm regionCategoryForm,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            log.error("表单验证错误，form={}",bindingResult.getFieldError().getDefaultMessage());
            throw new AdminException(
                    bindingResult.getFieldError().getDefaultMessage(),
                    "/sell/admin/index?token=" + regionCategoryForm.getToken());
        }

        RegionCategory regionCategory = new RegionCategory();
        BeanUtils.copyProperties(regionCategoryForm,regionCategory);
        if (StringUtils.isEmpty(regionCategoryForm.getRegionId())) {
            regionCategory.setRegionId(KeyUtil.getUUID());
        }
        regionCategoryService.save(regionCategory);

        return new ModelAndView("admin/common/success",
                AdminResultMapUtil.success(
                        AdminResultEnum.ADMIN_REGION_SUCCESS,
                        regionCategoryForm.getToken()));
    }

}
