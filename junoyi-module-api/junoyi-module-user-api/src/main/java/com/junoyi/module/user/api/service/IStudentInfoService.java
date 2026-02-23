package com.junoyi.module.user.api.service;

import com.junoyi.module.user.api.domain.entity.StudentInfo;
import com.junoyi.module.user.api.domain.vo.StudentInfoVO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface IStudentInfoService extends IService<StudentInfo> {
    List<StudentInfoVO> selectStudentInfoList(StudentInfo studentInfo);
    StudentInfoVO selectStudentInfoById(Long id);
    List<StudentInfoVO> selectStudentInfoListByParentId(Long parentId);
    int insertStudentInfo(StudentInfo studentInfo);
    int updateStudentInfo(StudentInfo studentInfo);
    int deleteStudentInfoByIds(Long[] ids);
}
