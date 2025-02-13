package com.dragont1ger.calendar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dragont1ger.calendar.dto.CalendarDto;
import com.dragont1ger.calendar.entity.Calendar;
import com.dragont1ger.calendar.repository.CalendarRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalendarService {

  private final CalendarRepository calendarRepository;

  // 새로운 이벤트 등록 가능
  public Calendar insert(CalendarDto calendarDto) {
    log.info("insert calendarDto==={}",calendarDto);

    // DTO에서 Entity로 변환하여 Builder로 객체 생성 
    Calendar calendar = Calendar.builder()
     .startDate(calendarDto.getStart())
     .endDate(calendarDto.getEnd())
     .title(calendarDto.getTitle())
     .allDay(calendarDto.isAllDay())
     .work(calendarDto.getWork())
     .build();
     // 데이터베이스에 저장
    Calendar savedCalendar = calendarRepository.save(calendar);
    return savedCalendar;
  }

  // 전체 이벤트 목록을 가져와서 DTO 리스트로 변환 후 반환
  public List<CalendarDto> getList() {
    List<Calendar> calendarList = calendarRepository.findAll();
    List<CalendarDto> calendarDtoList = new ArrayList<>();
    calendarList.forEach(cL -> {
      calendarDtoList.add(Calendar.fromEntity(cL));
    });
    return calendarDtoList;
  }

  // 이벤트 수정 기능 (트랜잭션 내에서 수행)
  @Transactional
  public Calendar change(CalendarDto calendarDto) {
    Optional<Calendar> optionalCalendar = calendarRepository.findById(calendarDto.getId());
    Calendar findedCalendar = null;
    log.info("calendarDto==={}",calendarDto);
    if(optionalCalendar.isPresent()) {
      findedCalendar = optionalCalendar.get();
      // 변경할 내용을 담은 새 Entity 생성 (혹은 기존 Entity 업데이트 가능)
      Calendar changeCalendar = Calendar.builder()
                                  .startDate(calendarDto.getStart())
                                  .endDate(calendarDto.getEnd())
                                  .title(calendarDto.getTitle())
                                  .allDay(calendarDto.isAllDay())
                                  .work(calendarDto.getWork())
                                  .build();
      // 변경된 Entity를 저장 후 반환
      return calendarRepository.save(changeCalendar);
    }
    return null;
  }
}