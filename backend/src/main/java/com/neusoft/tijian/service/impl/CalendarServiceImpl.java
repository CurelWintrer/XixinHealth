package com.neusoft.tijian.service.impl;

import com.neusoft.tijian.dto.CalendarRequestDto;
import com.neusoft.tijian.dto.CalendarResponseDto;
import com.neusoft.tijian.dto.OrdersMapperDto;
import com.neusoft.tijian.mapper.HospitalMapper;
import com.neusoft.tijian.mapper.OrdersMapper;
import com.neusoft.tijian.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private HospitalMapper hospitalMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    //生成预约日历
    @Override
    public List<CalendarResponseDto> listAppointmentCalendar(CalendarRequestDto dto) {
        //获取预约日期列表
        List<CalendarResponseDto> calendarList30 = getCalendarList30(dto.getHpId());
        //获取当前日历
        List<CalendarResponseDto> currentCalendarList = getCurrentyCalendarList(dto.getYear(),dto.getMonth());
        //给当前日历填充属性
        for(CalendarResponseDto curCalendar : currentCalendarList){
            for(CalendarResponseDto calendar : calendarList30){
                if (curCalendar.getYmd() != null) {
                    if(curCalendar.getYmd().equals(calendar.getYmd())){
                        curCalendar.setTotal(calendar.getTotal());
                        curCalendar.setExisting(calendar.getExisting());
                        curCalendar.setRemainder(calendar.getRemainder());
                    }
                }
            }
        }
        return currentCalendarList;
    }

    //获取预约日期列表
    private List<CalendarResponseDto> getCalendarList30(Integer hpId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        //做一个临时集合 作为去Orders表中查询的参数
        List<OrdersMapperDto> parameList = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            OrdersMapperDto dto = new OrdersMapperDto();
            calendar.add(Calendar.DATE, 1);//计算1天后的时间
            dto.setDate(sdf.format(calendar.getTime()));
            dto.setHpId(hpId);
            parameList.add(dto);
        }

        //根据parameList参数 查询30天的预约日期中 每一天的已预约人数
        //查询结果CalendarResponseDto中只有ymd existing两个属性
        List<CalendarResponseDto> calendarList30 = ordersMapper.listOrdersAppointmentNumber(parameList);

        //根据医院编号 获取预约规则 获取最大预约人数
        String[] strArr = hospitalMapper.getHospitalById(hpId).getRule().split(",");

        //继续填充返回值中的属性
        for (CalendarResponseDto cd : calendarList30) {
            //将预约日期转换为Calendar对象
            try {
                calendar.setTime(sdf.parse(cd.getYmd()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //通过Calendar对象的get方法获取某天为星期几 再通过strArr获取该天的预约人数
            int total = Integer.parseInt(strArr[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
            cd.setTotal(total);
            cd.setRemainder(total - cd.getExisting());
        }
        return calendarList30;
    }

    //获取当前年月的日历
    private List<CalendarResponseDto> getCurrentyCalendarList(Integer year, Integer month) {
        List<CalendarResponseDto> currentCalendar = new ArrayList<>();

        //计算从1900-01-01到现在的天数
        int totalDays = 0;
        for (int i = 1900; i < year; i++) {
            if (isLeap(i)) {
                totalDays += 366;
            } else {
                totalDays += 365;
            }
        }

        int beforeDays = 0;
        int currentDays = 0;

        for (int j = 1; j <= month; j++) {
            switch (j) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    currentDays = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    currentDays = 30;
                    break;
                case 2:
                    if (isLeap(year)) {
                        currentDays = 29;
                    } else {
                        currentDays = 28;
                    }
                    break;
            }
            if (j < month) {
                beforeDays += currentDays;
            }
        }
        totalDays += beforeDays;

        int firstDayOfMonth = totalDays % 7 + 1;
        if (firstDayOfMonth == 7) {
            firstDayOfMonth = 0;
        }

        for (int k = 0; k < firstDayOfMonth; k++) {
            currentCalendar.add(new CalendarResponseDto());
        }

        for (int i = 1; i <= currentDays; i++) {
            String m = month < 10 ? "0" + month : month + "";
            String d = i < 10 ? "0" + i : i + "";
            currentCalendar.add(new CalendarResponseDto(year + "-" + m + "-" + d));
        }
        return currentCalendar;
    }
    boolean isLeap(Integer year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
