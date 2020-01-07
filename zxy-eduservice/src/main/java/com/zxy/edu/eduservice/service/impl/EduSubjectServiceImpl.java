package com.zxy.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxy.edu.eduservice.entity.EduSubject;
import com.zxy.edu.eduservice.handler.EduException;
import com.zxy.edu.eduservice.mapper.EduSubjectMapper;
import com.zxy.edu.eduservice.service.EduSubjectService;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author zhangxy
 * @since 2020-01-07
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    /**
     * 读取Excel中的内容，添加到分类表里面
     *
     * @param file
     * @return
     */
    @Override
    public List<String> importSubject(MultipartFile file) {
        try {
            // 1. 获取文件输入流
            InputStream in = file.getInputStream();
            // 2. 创建workbook
            XSSFWorkbook workbook = new XSSFWorkbook(in);
            // 3. 通过workbook获取sheet
            XSSFSheet sheet = workbook.getSheetAt(0);

            // 存储错误信息
            List<String> msg = new ArrayList<>();

            // 4. sheet获取row（从第2行获取数据）
            // 循环遍历获取行
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                // 得到Excel中的每行数据
                XSSFRow row = sheet.getRow(i);
                // 如果行为空，提示错误信息
                if (row == null) {
                    String str = "表格数据为空，请输入数据";
                    msg.add(str);
                    return msg;
                }
                // 如果行不为空
                // 5.1 row获取第一列
                XSSFCell cellOne = row.getCell(0);
                // 判断列是否为空
                if (cellOne == null) {
                    String str = "第" + i + "行数据为空";
                    // 跳过这一行，往下继续执行
                    msg.add(str);
                    continue;
                }
                // 列不为空，获取第一列里面的数据
                // 一级分类的值
                String cellOneValue = cellOne.getStringCellValue();
                // 添加一级分类
                // 因为Excel里面有很多重复的一级分类，在添加一级分类之前要进行判断。
                // 判断要添加的一级分类在数据库表中是否存在，如果不存在则添加
                EduSubject eduSubjectExist = this.existOneSubject(cellOneValue);
                // 存储一级分类id
                String id_parent = null;

                if (eduSubjectExist == null) { // 如果不存在就进行添加
                    EduSubject eduSubject = new EduSubject();
                    eduSubject.setTitle(cellOneValue);
                    eduSubject.setParentId("0");
                    eduSubject.setSort(0);
                    baseMapper.insert(eduSubject);
                    // 获取新添加的一级分类id并进行赋值
                    id_parent = eduSubject.getId();
                } else {
                    // 把一级分类id赋值给id_parent
                    id_parent = eduSubjectExist.getId();
                }

                // 5.2 row获取第二列
                XSSFCell cellTwo = row.getCell(1);
                if (cellTwo == null) {
                    String str = "第" + i + "行数据为空";
                    // 跳过这一行，往下继续执行
                    msg.add(str);
                    continue;
                }
                // 不为空，获取第二列的值
                String cellTwoValue = cellTwo.getStringCellValue();
                // 添加二级分类
                // 判断数据库表是否存在二级分类，如果不存在则进行添加
                EduSubject twoSubjectExist = this.existTwoSubject(cellTwoValue, id_parent);
                if (twoSubjectExist == null) {
                    EduSubject twoSubject = new EduSubject();
                    twoSubject.setTitle(cellTwoValue);
                    twoSubject.setParentId(id_parent);
                    twoSubject.setSort(0);
                    baseMapper.insert(twoSubject);
                }
            }
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            throw new EduException(20001, "导入失败出现了异常！");
        }
    }

    /**
     * 判断数据库中是否存在二级分类
     *
     * @return
     */
    private EduSubject existTwoSubject(String name, String parentId) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        // 拼接条件
        wrapper.eq("title", name);
        wrapper.eq("parent_id", parentId);
        EduSubject eduSubject = baseMapper.selectOne(wrapper);
        return eduSubject;
    }

    /**
     * 判断数据库中是否存在一级分类
     *
     * @return
     */
    private EduSubject existOneSubject(String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        // 拼接条件
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        // 调用方法
        EduSubject eduSubject = baseMapper.selectOne(wrapper);
        return eduSubject;
    }
}