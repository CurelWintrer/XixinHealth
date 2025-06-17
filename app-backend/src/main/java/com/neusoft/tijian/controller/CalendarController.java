package com.neusoft.tijian.controller;

import com.neusoft.tijian.dto.CalendarRequestDto;
import com.neusoft.tijian.dto.CalendarResponseDto;
import com.neusoft.tijian.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    //生成预约日历
    @RequestMapping("/listAppointmentCalendar")
    public List<CalendarResponseDto> listAppointmentCalendar(@RequestBody CalendarRequestDto dto) {
        return calendarService.listAppointmentCalendar(dto);
    }

}
