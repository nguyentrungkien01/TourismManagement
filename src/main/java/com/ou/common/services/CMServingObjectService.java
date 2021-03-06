package com.ou.common.services;

import com.ou.pojos.ServingObjectEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CMServingObjectService {
    JSONArray getServingObjects(Integer pageIndex);
    long getServingObjectAmount();
    ServingObjectEntity getServingObjectAsObj(String svoSlug);
    ServingObjectEntity getServingObject(Integer svoId);

    JSONObject getServingObjectAsJsonObj(String svoSlug);

    boolean createServingObject(ServingObjectEntity servingObjectEntity);

    boolean updateServingObject(ServingObjectEntity servingObjectEntity);

    boolean deleteServingObject(ServingObjectEntity servingObjectEntity);
}
