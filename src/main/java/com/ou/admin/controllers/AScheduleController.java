package com.ou.admin.controllers;

import com.ou.common.services.CMScheduleService;
import com.ou.common.services.CMTourService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.ScheduleEntity;
import com.ou.pojos.TourEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@RequestMapping(path = "/quan-tri-vien/lich-trinh")
public class AScheduleController {

    @Autowired
    private CMScheduleService cMScheduleService;

    @Autowired
    private CMTourService cMTourService;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    // get
    @GetMapping()
    public String getSchedulesView() {
        return "a-schedule";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getSchedulesInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("page"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray schedules = cMScheduleService.getSchedules(pageIndex);
        return new ResponseEntity<>(schedules, schedules.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    // create
    @GetMapping("/tao-moi")
    public String getScheduleCreatedView() {
        return "a-schedule-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> createSchedule(HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        ScheduleEntity scheduleEntity = pojoBeanFactory.getApplicationContext().getBean(ScheduleEntity.class);
        TourEntity tourEntity = cMTourService.getTourAsObj(httpServletRequest.getParameter("tourSlug"));
        scheduleEntity.setScheTitle(httpServletRequest.getParameter("scheTitle"));
        scheduleEntity.setScheContent(httpServletRequest.getParameter("scheContent"));
        scheduleEntity.setTourId(tourEntity.getTourId());
        boolean createdResult = cMScheduleService.createSchedule(scheduleEntity);
        return new ResponseEntity<>(createdResult ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    // update
    @GetMapping("/{scheSlug}")
    public String getScheduleEditedView(@PathVariable String scheSlug) {
        return "a-schedule-updated";
    }

    @GetMapping("/{scheSlug}/chinh-sua")
    public ResponseEntity<JSONObject> getScheduleDetail(@PathVariable String scheSlug) {
        JSONObject schedule = cMScheduleService.getScheduleAsJsonObj(scheSlug);
        if (schedule == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @RequestMapping(value = "/{scheSlug}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> updateSchedule(@PathVariable String scheSlug, HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        ScheduleEntity schedule = cMScheduleService.getScheduleAsObj(scheSlug);
        TourEntity tour = cMTourService.getTourAsObj(httpServletRequest.getParameter("tourSlug"));
        if (schedule == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        schedule.setScheTitle(httpServletRequest.getParameter("scheTitle"));
        schedule.setScheContent(httpServletRequest.getParameter("scheContent"));
        schedule.setTourId(tour.getTourId());
        boolean updateResult = cMScheduleService.updateSchedule(schedule);
        return new ResponseEntity<>(updateResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    // delete
    @RequestMapping(value = "/{scheSlug}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteSchedule(@PathVariable String scheSlug) {
        ScheduleEntity schedule = cMScheduleService.getScheduleAsObj(scheSlug);
        if (schedule == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        boolean deleteResult = cMScheduleService.deleteSchedule(schedule);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
