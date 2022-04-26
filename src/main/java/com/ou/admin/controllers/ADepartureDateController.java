package com.ou.admin.controllers;

import com.ou.common.services.CMDepartureDateService;
import com.ou.common.services.CMFeatureService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.DepartureDateEntity;
import com.ou.pojos.FeatureEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Map;

@Controller
@RequestMapping(path = "/quan-tri-vien/ngay-khoi-hanh")
public class ADepartureDateController {

    @Autowired
    private CMDepartureDateService cMDepartureDateService;

    @Autowired
    private CMFeatureService cMFeatureService;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;


    // get
    @GetMapping()
    public String getDepartureDatesView() {
        return "a-departure-date";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getDepartureDatesInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("page"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray departureDates = cMDepartureDateService.getDepartureDats(pageIndex);
        return new ResponseEntity<>(departureDates, departureDates.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    // create
    @GetMapping("/tao-moi")
    public String getDepartureDateCreatedView() {
        return "a-departure-date-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> createDepartureDate(HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        DepartureDateEntity departureDate = pojoBeanFactory.getApplicationContext().getBean(DepartureDateEntity.class);
        FeatureEntity feature = cMFeatureService.getFeatureAsObj(httpServletRequest.getParameter("feaSlug"));
        departureDate.setDptDate(Timestamp.valueOf(httpServletRequest.getParameter("dptDate")));
        departureDate.setFeaId(feature.getFeaId());
        boolean createdResult = cMDepartureDateService.createDepartureDat(departureDate);
        return new ResponseEntity<>(createdResult ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    // update
    @GetMapping("/{dptId}")
    public String getDepartureDateEditedView(@PathVariable Integer dptId) {
        return "a-departure-date-updated";
    }

    @GetMapping("/{dptId}/chinh-sua")
    public ResponseEntity<JSONObject> getDepartureDateDetail(@PathVariable Integer dptId) {
        JSONObject departureDate = cMDepartureDateService.getDepartureDatAsJsonObj(dptId);
        if (departureDate == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(departureDate, HttpStatus.OK);
    }

    @RequestMapping(value = "/{dptId}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> updateDepartureDate(@PathVariable Integer dptId, HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        DepartureDateEntity departureDate = cMDepartureDateService.getDepartureDatAsObj(dptId);
        FeatureEntity feature = cMFeatureService.getFeatureAsObj(httpServletRequest.getParameter("feaSlug"));
        if (departureDate == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        departureDate.setDptDate(Timestamp.valueOf(httpServletRequest.getParameter("dptDate")));
        departureDate.setFeaId(feature.getFeaId());
        boolean updateResult = cMDepartureDateService.updateDepartureDat(departureDate);
        return new ResponseEntity<>(updateResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    // delete
    @RequestMapping(value = "/{dptId}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteDepartureDate(@PathVariable Integer dptId) {
        DepartureDateEntity departureDate = cMDepartureDateService.getDepartureDatAsObj(dptId);
        if (departureDate == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        boolean deleteResult = cMDepartureDateService.deleteDepartureDat(departureDate);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
