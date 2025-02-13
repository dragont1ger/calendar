package com.dragont1ger.calendar.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString // 객체 정보를 문자열로 출력하는 기능 제공
public class CalendarDto {
  private Integer id;     // 이벤트 식별자
  private String title;   // 이벤트 제목
  private String start;   // 시작 날짜 (시간 포함 문자열)
  private String end;     // 종료 날짜 (시간 포함 문자열)
  private String work;    // 업무 구분(색상 코드로 사용)
  private boolean allDay; // 종일 여부
}
