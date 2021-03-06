package com.ou.admin.controllers;


import com.ou.common.services.CMServingObjectService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.ServingObjectEntity;
import com.ou.utils.PageUtil;
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
@RequestMapping(path = "/quan-tri-vien/doi-tuong-phuc-vu")
public class AServingObjectController {

    @Autowired
    private CMServingObjectService cMServingObjectService;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    // get
    @GetMapping()
    public String getServingObjectsView() {
        return "a-serving-object";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getServingObjectsInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("trang"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray servingObjects = cMServingObjectService.getServingObjects(pageIndex);
        return new ResponseEntity<>(servingObjects, servingObjects.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/so-trang")
    public ResponseEntity<JSONObject> getServingObjectPageAmount(){
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
        jsonObject.put("pageAmount",pageUtil.getPageAmount(cMServingObjectService.getServingObjectAmount()));
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
    // create
    @GetMapping("/tao-moi")
    public String getServingObjectCreatedView() {
        return "a-serving-object-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createServingObject(HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        ServingObjectEntity servingObjectEntity = pojoBeanFactory.getApplicationContext().getBean(ServingObjectEntity.class);
        servingObjectEntity.setSvoName(httpServletRequest.getParameter("svoName"));
        boolean createdResult = cMServingObjectService.createServingObject(servingObjectEntity);
        if (createdResult)
            return "redirect:/quan-tri-vien/doi-tuong-phuc-vu";
        return "redirect:/quan-tri-vien/doi-tuong-phuc-vu/tao-moi?error=1";
    }

    // update
    @GetMapping("/{svoSlug}")
    public String getServingObjectEditedView(@PathVariable String svoSlug) {
        return "a-serving-object-updated";
    }

    @GetMapping("/{svoSlug}/chinh-sua")
    public ResponseEntity<JSONObject> getServingObjectDetail(@PathVariable String svoSlug) {
        JSONObject servingObject = cMServingObjectService.getServingObjectAsJsonObj(svoSlug);
        if (servingObject == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(servingObject, HttpStatus.OK);
    }

    @RequestMapping(value = "/{svoSlug}", method = RequestMethod.POST)
    public String updateServingObject(@PathVariable String svoSlug, HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        ServingObjectEntity servingObject = cMServingObjectService.getServingObjectAsObj(svoSlug);
        if (servingObject == null)
            return String.format("redirect:/quan-tri-vien/doi-tuong-phuc-vu/%s?error=1", svoSlug);
        servingObject.setSvoName(httpServletRequest.getParameter("svoName"));
        boolean updateResult = cMServingObjectService.updateServingObject(servingObject);
        if (updateResult)
            return "redirect:/quan-tri-vien/doi-tuong-phuc-vu";
        return String.format("redirect:/quan-tri-vien/doi-tuong-phuc-vu/%s?error=1", svoSlug);
    }

    // delete
    @RequestMapping(value = "/{svoSlug}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteServingObject(@PathVariable String svoSlug) {
        ServingObjectEntity servingObject = cMServingObjectService.getServingObjectAsObj(svoSlug);
        if (servingObject == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        boolean deleteResult = cMServingObjectService.deleteServingObject(servingObject);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
