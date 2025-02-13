package com.dragont1ger.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dragont1ger.calendar.entity.Calendar;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer>  {

}
