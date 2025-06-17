package com.neusoft.tijian.service;

import com.neusoft.tijian.dto.CalendarRequestDto;
import com.neusoft.tijian.dto.CalendarResponseDto;

import java.util.List;

public interface CalendarService {

    //生成预约日历
    List<CalendarResponseDto> listAppointmentCalendar(CalendarRequestDto calendarRequestDto);
}
