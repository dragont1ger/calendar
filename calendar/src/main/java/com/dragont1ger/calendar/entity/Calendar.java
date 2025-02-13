package com.dragont1ger.calendar.entity;

import com.dragont1ger.calendar.dto.CalendarDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 보호
@Getter
@Table(name="table_calendar") // 데이터베이스 테이블 이름 지정
public class Calendar {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE) // 시퀀스 기반의 ID 생성 전략
  private Integer id;


  private String startDate; // 이벤트 시작 날짜 및 시간
  private String endDate;   // 이벤트 종료 날짜 및 시간
  private String title;     // 이벤트 제목
  private Boolean allDay;   // 종일 이벤트 여부 (21c 이상에서는 true / false  boolean값 있음 이하 버전에서는 없음)
  private String work;      // 업무 종류 또는 중요도

  @Builder  // 빌더 패턴을 통한 객체 생성 지원
  public Calendar(Integer id,String startDate,String endDate,String title,String work,  Boolean allDay) {
    this.id = id;
    this.title=title;
    this.startDate=startDate;
    this.endDate=endDate;   
    this.work=work;
    this.allDay=allDay;
    
  }

  // 각 필드를 업데이트하는 메서드들 (필요에 따라 사용)
  public void updateStartDate(String startDate) {
    this.startDate = startDate;
  }
  
  public void updateEndDate(String endDate) {
    this.endDate = endDate;
  }

  public void updateTitle(String title) {
    this.title = title;
  }

  public void updateWork(String work) {
    this.work = work;
  }


  public void updateDate(String startDate, String endDate) {
    this.updateStartDate(startDate);
    this.updateEndDate(endDate);
  }



  
  

  public void updateAll(String startDate, String endDate, String title, String work) {
    this.updateStartDate(startDate);
    this.updateEndDate(endDate);
    this.updateTitle(title);
    this.updateWork(work);
  }
  
  // Entity를 DTO로 변환하는 메서드
  public static CalendarDto fromEntity(Calendar calendar) {
        return CalendarDto.builder()
                .id(calendar.getId())
                .title(calendar.getTitle())
                .start(calendar.getStartDate())
                .end(calendar.getEndDate())
                .work(calendar.getWork())
                .allDay(calendar.getAllDay())
                .build();
    }
}
