package com.ou.common.services.impl;

import com.ou.common.repositories.*;
import com.ou.common.services.CMBillService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CMBillServiceImpl implements CMBillService {

    @Autowired
    private CMBillRepository cMBillRepository;

    @Autowired
    private CMServingObjectRepository cMServingObjectRepository;

    @Autowired
    private CMTourServingObjectRepository cMTourServingObjectRepository;

    @Autowired
    private CMTourRepository cMTourRepository;

    @Autowired
    private CMPostRepository cMPostRepository;
    @Autowired
    private CMBillTourServingObjectRepository cMBillTourServingObjectRepository;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    private JSONObject getBillInfo(Object[] bill){
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        jsonObject.put("accId", bill[0]);
        jsonObject.put("accUsername", bill[1]);
        jsonObject.put("accFirstName", bill[2]);
        jsonObject.put("accLastName", bill[3]);
        jsonObject.put("accIdCard", bill[4]);
        jsonObject.put("accPhoneNumber", bill[5]);
        jsonObject.put("accDateOfBirth", bill[6]);

        jsonObject.put("paytId", bill[7]);
        jsonObject.put("paytName", bill[8]);
        jsonObject.put("paytSlug", bill[9]);

        jsonObject.put("billId", bill[10]);
        jsonObject.put("billCreatedDate", bill[11]);
        jsonObject.put("billTotalMoney", bill[12]);
        jsonObject.put("billTotalSaleMoney", bill[13]);
        jsonObject.put("billShipDate", bill[14]);
        jsonObject.put("billShipAddress", bill[15]);
        jsonObject.put("billShipDistrict", bill[16]);
        jsonObject.put("billShipCity", bill[17]);
        jsonObject.put("billIsPaid", bill[18]);
        jsonObject.put("billDepartureDate", bill[19]);

        JSONArray tourPrices = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        List<BillTourServingObjectEntity> billTourServingObjectEntities =
                cMBillTourServingObjectRepository.getBillTourServingObjectByBill((Integer) bill[10]);
        AtomicReference<Integer> tourId = utilBeanFactory.getApplicationContext()
                .getBean("atomicReference", AtomicReference.class);

        billTourServingObjectEntities.forEach(billTourServingObjectEntity -> {
            TourServingObjectEntity tourServingObject =
                    cMTourServingObjectRepository.getTourServingObjectById(billTourServingObjectEntity.getTsvoId());
            ServingObjectEntity servingObjectEntity =
                    cMServingObjectRepository.getServingObject(tourServingObject.getSvoId());
            tourId.set(tourServingObject.getTourId());
            JSONObject jsonObject1 = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject1.put("tourAmount", billTourServingObjectEntity.getTourAmount());
            jsonObject1.put("tourPrice", tourServingObject.getTourPrice());
            jsonObject1.put("svoName", servingObjectEntity.getSvoName());
            jsonObject1.put("svoSlug", servingObjectEntity.getSvoSlug());
            tourPrices.add(jsonObject1);
        });
        jsonObject.put("tourPrices", tourPrices);
        TourEntity tour = cMTourRepository.getTour(tourId.get());
        PostEntity post = cMPostRepository.getPost(tourId.get());
        jsonObject.put("postId", post.getPostId());
        jsonObject.put("postTitle", post.getPostTitle());
        jsonObject.put("postSlug", post.getPostSlug());
        jsonObject.put("tourAverageRating", tour.getTourAverageRating());
        return jsonObject;
    }
    @Override
    public JSONArray getBills(Integer pageIndex) {
        List<Object[]> bills = cMBillRepository.getBills(pageIndex);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        bills.forEach(bill -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("accUsername", bill[0]);
            jsonObject.put("paymentType", bill[1]);
            jsonObject.put("billId", bill[2]);
            jsonObject.put("billCreatedDate", bill[3]);
            jsonObject.put("billTotalMoney",bill[4] );
            jsonObject.put("billTotalSaleMoney", bill[5]);
            jsonObject.put("billIsPaid",bill[6] );
            jsonObject.put("billDepartureDate", bill[7]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public long getBillAmount() {
        return cMBillRepository.getBillAmount();
    }

    @Override
    public BillEntity getBillAsObj(Integer billId) {
        if(billId==null)
            return null;
        return cMBillRepository.getBillAsObj(billId);
    }

    @Override
    public JSONObject getBillAsJson(Integer billId) {
        if (billId == null)
            return null;
        Object[] bill = cMBillRepository.getBillAsJson(billId);
        if (bill != null)
            return getBillInfo(bill);

        return null;
    }

    @Override
    public BillEntity createBill(BillEntity bill) {
        bill.setBillCreatedDate(utilBeanFactory.getApplicationContext().getBean("currentTimeStamp", Timestamp.class));
        Integer billId= cMBillRepository.createBill(bill);
        BillEntity billEntity = cMBillRepository.getBillAsObj(billId);
        return billEntity;
    }

    @Override
    public boolean updateBill(BillEntity billEntity) {
        return cMBillRepository.updateBill(billEntity);
    }
}
