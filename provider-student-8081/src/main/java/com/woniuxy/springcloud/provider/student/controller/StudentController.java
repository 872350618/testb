package com.woniuxy.springcloud.provider.student.controller;

import com.woniuxy.springcloud.common.entity.ResponseResult;
import com.woniuxy.springcloud.common.entity.Student;
import com.woniuxy.springcloud.provider.student.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="Student管理接口")
//@CrossOrigin
@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/ok")
    public ResponseResult ok(){
        return new ResponseResult(200,"ok");
    }

    @RequestMapping("/timeout")
    public ResponseResult timeout(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResponseResult(200,"timeout");
    }

    @ApiOperation("添加学生")
    //post请求在分布式服务中心，对外提供服务,对象必须使用@RequestBody
    @PostMapping("/")
    public ResponseResult addStudent(@ApiParam(name = "student",value = "学生") @RequestBody Student student){
        studentService.addStudent(student);
        return new ResponseResult(200,"success"+port);
    }


    @GetMapping("/{sid}")
    public ResponseResult<Student> getStudentBySid(@PathVariable("sid") Integer sid){
        Student student = studentService.getStudentBySid(sid);
        return new ResponseResult<Student>(200,"success"+port,student);
    }

    //@RequiresPermssions("select:student")
    @GetMapping("/")
    public ResponseResult<List<Student>> getAllStudents(){
        List<Student> students = studentService.getAllStudents();
        return new ResponseResult<List<Student>>(200,"success"+port,students);
    }
}
