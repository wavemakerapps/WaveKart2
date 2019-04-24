/*Copyright (c) 2015-2016 WaveMaker.com All Rights Reserved.
 This software is the confidential and proprietary information of WaveMaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with WaveMaker.com*/
package com.wavemaker.sampleapps.wavekart.eshopping.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.export.ExportOptions;

import com.wavemaker.sampleapps.wavekart.eshopping.models.query.*;

@Deprecated
public interface EshoppingQueryExecutorService_V1 {

    @Deprecated
    public Page<Object> executeTotalItemsPrice_CartProd(Pageable pageable, Integer cartId);

    @Deprecated
    public Page<Object> executeTotalItemsPrice_Orders(Pageable pageable, Integer oid);

    @Deprecated
    public int executeDeleteCartItems_LoggedUser(Integer loggedUserCartId);

    @Deprecated
    public Page<Object> executeGet_CartIdByUserid(Pageable pageable);

    @Deprecated
    public Page<Object> executeMyCart_Items_Count(Pageable pageable);

    @Deprecated
    public Page<Object> executeGet_CartItems_id(Pageable pageable, Integer cid);

}