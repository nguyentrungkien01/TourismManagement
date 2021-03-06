package com.ou.admin.controllers;


import com.ou.common.services.CMSalePercentService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.SalePercentEntity;
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
@RequestMapping(path = "/quan-tri-vien/phan-tram-giam-gia")
public class ASalePercentController {

    @Autowired
    private CMSalePercentService cMSalePercentService;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    // get
    @GetMapping()
    public String getSalePercentsView() {
        return "a-sale-percent";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getSalePercentsInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("trang"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray salePercents = cMSalePercentService.getSalePercents(pageIndex);
        return new ResponseEntity<>(salePercents, salePercents.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/so-trang")
    public ResponseEntity<JSONObject> getSalePercentPageAmount(){
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
        jsonObject.put("pageAmount",pageUtil.getPageAmount(cMSalePercentService.getSalePercentAmount()));
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    // create
    @GetMapping("/tao-moi")
    public String getSalePercentCreatedView() {
        return "a-sale-percent-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createSalePercent(HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        SalePercentEntity salePercentEntity = pojoBeanFactory.getApplicationContext().getBean(SalePercentEntity.class);
        salePercentEntity.setSperPercent(Integer.valueOf(httpServletRequest.getParameter("sperPercent")));
        boolean createdResult = cMSalePercentService.createSalePercent(salePercentEntity);
        if (createdResult)
            return "redirect:/quan-tri-vien/phan-tram-giam-gia";
        return "redirect:/quan-tri-vien/phan-tram-giam-gia/tao-moi?error=1";
    }

    // update
    @GetMapping("/{sperId}")
    public String getSalePercentEditedView(@PathVariable Integer sperId) {
        return "a-sale-percent-updated";
    }

    @GetMapping("/{sperId}/chinh-sua")
    public ResponseEntity<JSONObject> getSalePercentDetail(@PathVariable Integer sperId) {
        JSONObject salePercent = cMSalePercentService.getSalePercentAsJsonObj(sperId);
        if (salePercent == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(salePercent, HttpStatus.OK);
    }

    @RequestMapping(value = "/{sperId}", method = RequestMethod.POST)
    public String updateSalePercent(@PathVariable Integer sperId,
                                                        HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        SalePercentEntity salePercent = cMSalePercentService.getSalePercentAsObj(sperId);
        if (salePercent == null)
            return String.format("redirect:/quan-tri-vien/phan-tram-giam-gia/%d?error=1", sperId);
        salePercent.setSperPercent(Integer.valueOf(httpServletRequest.getParameter("sperPercent")));
        boolean updateResult = cMSalePercentService.updateSalePercent(salePercent);
        if (updateResult)
            return "redirect:/quan-tri-vien/phan-tram-giam-gia";
        return String.format("redirect:/quan-tri-vien/phan-tram-giam-gia/%d?error=1", sperId);
    }

    // delete
    @RequestMapping(value = "/{sperId}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteSalePercent(@PathVariable Integer sperId) {
        SalePercentEntity salePercent = cMSalePercentService.getSalePercentAsObj(sperId);
        if (salePercent == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        boolean deleteResult = cMSalePercentService.deleteSalePercent(salePercent);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
