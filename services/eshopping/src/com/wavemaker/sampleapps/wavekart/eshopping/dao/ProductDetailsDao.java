/*Copyright (c) 2015-2016 WaveMaker.com All Rights Reserved.
 This software is the confidential and proprietary information of WaveMaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with WaveMaker.com*/
package com.wavemaker.sampleapps.wavekart.eshopping.dao;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wavemaker.runtime.data.dao.WMGenericDaoImpl;

import com.wavemaker.sampleapps.wavekart.eshopping.ProductDetails;

/**
 * Specifies methods used to obtain and modify ProductDetails related information
 * which is stored in the database.
 */
@Repository("eshopping.ProductDetailsDao")
public class ProductDetailsDao extends WMGenericDaoImpl<ProductDetails, Integer> {

    @Autowired
    @Qualifier("eshoppingTemplate")
    private HibernateTemplate template;


    @Override
    public HibernateTemplate getTemplate() {
        return this.template;
    }
}