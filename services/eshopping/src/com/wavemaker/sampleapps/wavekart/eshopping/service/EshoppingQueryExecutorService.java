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

public interface EshoppingQueryExecutorService {

    Page<TotalItemsPriceCartProdResponse> executeTotalItemsPrice_CartProd(Integer cartId, Pageable pageable);

    void exportTotalItemsPrice_CartProd(Integer cartId, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream);

    Page<TotalItemsPriceOrdersResponse> executeTotalItemsPrice_Orders(Integer oid, Pageable pageable);

    void exportTotalItemsPrice_Orders(Integer oid, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream);

    Integer executeDeleteCartItems_LoggedUser(Integer loggedUserCartId);

    Page<GetCartIdByUseridResponse> executeGet_CartIdByUserid(Pageable pageable);

    void exportGet_CartIdByUserid(ExportOptions exportOptions, Pageable pageable, OutputStream outputStream);

    Page<MyCartItemsCountResponse> executeMyCart_Items_Count(Pageable pageable);

    void exportMyCart_Items_Count(ExportOptions exportOptions, Pageable pageable, OutputStream outputStream);

    Page<QvGetInventoryDetailsResponse> executeQV_getInventoryDetails(List<Integer> productIds, Pageable pageable);

    void exportQV_getInventoryDetails(List<Integer> productIds, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream);

    Page<GetCartItemsIdResponse> executeGet_CartItems_id(Integer cid, Pageable pageable);

    void exportGet_CartItems_id(Integer cid, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream);

    Integer executeQV_UpdateQuantityFromPI(QvUpdateQuantityFromPiRequest qvUpdateQuantityFromPiRequest);

}