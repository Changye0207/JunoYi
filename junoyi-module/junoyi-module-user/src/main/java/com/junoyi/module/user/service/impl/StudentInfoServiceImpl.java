package com.junoyi.module.user.service.impl;

import com.junoyi.module.user.api.domain.entity.StudentInfo;
import com.junoyi.module.user.api.domain.vo.StudentInfoVO;
import com.junoyi.module.user.api.mapper.StudentInfoMapper;
import com.junoyi.module.user.api.service.IStudentInfoService;
import com.junoyi.module.user.convert.StudentInfoConvert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo>
        implements IStudentInfoService {

    @Override
    public List<StudentInfoVO> selectStudentInfoList(StudentInfo studentInfo) {
        LambdaQueryWrapper<StudentInfo> wrapper = new LambdaQueryWrapper<>();
        if (studentInfo.getParentId() != null) {
            wrapper.eq(StudentInfo::getParentId, studentInfo.getParentId());
        }
        if (StringUtils.hasText(studentInfo.getName())) {
            wrapper.like(StudentInfo::getName, studentInfo.getName());
        }
        List<StudentInfo> list = list(wrapper);
        return StudentInfoConvert.INSTANCE.toVOList(list);
    }

    @Override
    public StudentInfoVO selectStudentInfoById(Long id) {
        StudentInfo studentInfo = getById(id);
        return StudentInfoConvert.INSTANCE.toVO(studentInfo);
    }

    @Override
    public List<StudentInfoVO> selectStudentInfoListByParentId(Long parentId) {
        LambdaQueryWrapper<StudentInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentInfo::getParentId, parentId);
        List<StudentInfo> list = list(wrapper);
        return StudentInfoConvert.INSTANCE.toVOList(list);
    }

    @Override
    public int insertStudentInfo(StudentInfo studentInfo) {
        return baseMapper.insert(studentInfo);
    }

    @Override
    public int updateStudentInfo(StudentInfo studentInfo) {
        return baseMapper.updateById(studentInfo);
    }

    @Override
    public int deleteStudentInfoByIds(Long[] ids) {
        return baseMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
