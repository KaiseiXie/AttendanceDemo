package com.example.attendancedemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.attendancedemo.common.R;
import com.example.attendancedemo.entity.Attendance;
import com.example.attendancedemo.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    /**
     * 出勤記録一覧表
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(HttpServletRequest req,int page,int pageSize){

        //验证当前用户是否为管理员
        if (req.getSession().getAttribute("employee").toString().equals("1")){
            log.info("收到前端发送的查询请求");
        }else return R.error("権限がない");

        //使用分页构造器
        Page<Attendance> pageInfo = new Page<>(page, pageSize);

        //构建条件查找器
        LambdaQueryWrapper<Attendance> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc();

        attendanceService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }

    @GetMapping("/list")
    public R<List<Attendance>> list(HttpServletRequest request){

        String attendanceId = request.getSession().getAttribute("attendance").toString();
        LambdaQueryWrapper<Attendance> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(!attendanceId.isEmpty(),Attendance::getTimesIn,attendanceId);

        return R.success(attendanceService.list(queryWrapper));
    }

    /**
     * idにっよて、attendanceデータを削除
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long timesIn){

        //通过更新其更新数据库中对应表格
        LambdaUpdateWrapper<Attendance> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Attendance::getTimesIn,timesIn);
        boolean remove = attendanceService.remove(updateWrapper);

        //检查操作是否成功，并返回相应结果
        if (remove){
        return R.success("削除完了しました");
        }
        else return R.error("操作失敗");
    }

    /**
     * フロントエンドからデータによって、DBに更新
     * @param attendance
     * @return
     */
    @PutMapping
    public R<String> edit(HttpServletRequest request,@RequestBody Attendance attendance){


        //验证当前用户是否为管理员
        if (request.getSession().getAttribute("employee").toString().equals("1")){
            log.info("收到前端发送的查询请求");
        }else return R.error("権限がない");

        //获取要修改数据的考勤编号
        String workTime = attendanceService.timeCalculator(attendance.getClockInTime(),attendance.getClockOutTime());

        //创建更新构造器
        LambdaUpdateWrapper<Attendance> updateWrapper = new LambdaUpdateWrapper();
        //执行更新操作
        updateWrapper.eq(Attendance::getTimesIn,attendance.getTimesIn());
        updateWrapper.set(Attendance::getClockInTime,attendance.getClockInTime());
        updateWrapper.set(Attendance::getClockOutTime,attendance.getClockOutTime());
        updateWrapper.set(Attendance::getWorkTime,workTime);

        attendanceService.update(updateWrapper);

        return R.success("修正完了");
    }

}
