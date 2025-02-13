package com.dragont1ger.calendar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dragont1ger.calendar.dto.CalendarDto;
import com.dragont1ger.calendar.entity.Calendar;
import com.dragont1ger.calendar.service.CalendarService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/calendar")
@Slf4j
@RequiredArgsConstructor
public class CalendarController {


  private final CalendarService calendarService;

  @GetMapping("/main")
  public String main() {
      return "/calendar/main";
  }

  // 달력 이벤트 리스트를 JSON 형태로 반환하는 API
  @GetMapping("/list")
  @ResponseBody
  public List<CalendarDto> list() {
      List<CalendarDto> calendarList = calendarService.getList();
      return calendarList;
  }

  // 새로운 달력 이벤트를 등록하는 API (JSON 데이터를 RequestBody로 받음)
  @PostMapping("/insert")
  @ResponseBody
  public Map<String,String> insert(@RequestBody CalendarDto calendarDto) {
    log.info("calendarDto=={}",calendarDto.toString());
    Calendar savedcalendar = calendarService.insert(calendarDto);
    Map<String,String>  resultMap = new HashMap<>();
    if(savedcalendar!=null) {
      resultMap.put("isInsert", "ok");
    } else {
      resultMap.put("isInsert", "fail");
    }
    return resultMap;
  }

  // 기존 달력 이벤트를 변경하는 API (JSON 데이터를 RequestBody로 받음)
  @PostMapping("/change")
  @ResponseBody
  public Map<String,String> change(@RequestBody CalendarDto calendarDto) {
    log.info("calendarDto=={}",calendarDto.toString());
    Calendar changedCalendar = calendarService.change(calendarDto);
    Map<String,String>  resultMap = new HashMap<>();
    if(changedCalendar!=null) {
      resultMap.put("isChange", "ok");
    } else {
      resultMap.put("isChange", "fail");
    }
    return resultMap;
  }

  // 예시 : GET 방식으로 파라미터를 받아 처리하는 메서드 (현재는 빈 문자열 반환)
  @GetMapping("path")
  public String getMethodName(@RequestParam String param) {
      return new String();
  }
  
}
