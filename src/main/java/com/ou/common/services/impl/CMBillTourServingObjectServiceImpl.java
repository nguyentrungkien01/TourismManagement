package com.ou.common.services.impl;


import com.ou.common.repositories.CMBillTourServingObjectRepository;
import com.ou.common.services.CMBillTourServingObjectService;
import com.ou.pojos.BillTourServingObjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMBillTourServingObjectServiceImpl implements CMBillTourServingObjectService {

    @Autowired
    private CMBillTourServingObjectRepository cmBillTourServingObjectRepository;


    @Override
    public List<BillTourServingObjectEntity> getBillTourServingObjectByBill(Integer billId) {
        if (billId == null )
            return null;
        return cmBillTourServingObjectRepository.getBillTourServingObjectByBill(billId);
    }

    @Override
    public List<BillTourServingObjectEntity> getBillTourServingObjectByTourServingObject(Integer tsvoId) {
        if (tsvoId == null )
            return null;
        return cmBillTourServingObjectRepository.getBillTourServingObjectByTourServingObject(tsvoId);
    }

    @Override
    public boolean createBillTourServingObject(BillTourServingObjectEntity billTourServingObjectEntity) {
        return cmBillTourServingObjectRepository.createBillTourServingObject(billTourServingObjectEntity);
    }

    @Override
    public boolean updateBillTourServingObject(BillTourServingObjectEntity billTourServingObjectEntity) {
        return cmBillTourServingObjectRepository.updateBillTourServingObject(billTourServingObjectEntity);
    }

    @Override
    public boolean deleteBillTourServingObject(BillTourServingObjectEntity billTourServingObjectEntity) {
        return cmBillTourServingObjectRepository.deleteBillTourServingObject(billTourServingObjectEntity);

    }
}
