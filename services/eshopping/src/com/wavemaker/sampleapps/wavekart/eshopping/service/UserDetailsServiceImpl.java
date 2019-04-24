/*Copyright (c) 2015-2016 WaveMaker.com All Rights Reserved.
 This software is the confidential and proprietary information of WaveMaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with WaveMaker.com*/
package com.wavemaker.sampleapps.wavekart.eshopping.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.wavemaker.sampleapps.wavekart.eshopping.CartDetails;
import com.wavemaker.sampleapps.wavekart.eshopping.Orders;
import com.wavemaker.sampleapps.wavekart.eshopping.UserAddressDetails;
import com.wavemaker.sampleapps.wavekart.eshopping.UserDetails;


/**
 * ServiceImpl object for domain model class UserDetails.
 *
 * @see UserDetails
 */
@Service("eshopping.UserDetailsService")
@Validated
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("eshopping.UserAddressDetailsService")
    private UserAddressDetailsService userAddressDetailsService;

    @Lazy
    @Autowired
    @Qualifier("eshopping.OrdersService")
    private OrdersService ordersService;

    @Autowired
    @Qualifier("eshopping.UserDetailsDao")
    private WMGenericDao<UserDetails, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<UserDetails, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "eshoppingTransactionManager")
    @Override
    public UserDetails create(UserDetails userDetails) {
        LOGGER.debug("Creating a new UserDetails with information: {}", userDetails);

        UserDetails userDetailsCreated = this.wmGenericDao.create(userDetails);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(userDetailsCreated);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public UserDetails getById(Integer userdetailsId) {
        LOGGER.debug("Finding UserDetails by id: {}", userdetailsId);
        return this.wmGenericDao.findById(userdetailsId);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public UserDetails findById(Integer userdetailsId) {
        LOGGER.debug("Finding UserDetails by id: {}", userdetailsId);
        try {
            return this.wmGenericDao.findById(userdetailsId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No UserDetails found with id: {}", userdetailsId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public List<UserDetails> findByMultipleIds(List<Integer> userdetailsIds, boolean orderedReturn) {
        LOGGER.debug("Finding UserDetails by ids: {}", userdetailsIds);

        return this.wmGenericDao.findByMultipleIds(userdetailsIds, orderedReturn);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public UserDetails getByEmailAddress(String emailAddress) {
        Map<String, Object> emailAddressMap = new HashMap<>();
        emailAddressMap.put("emailAddress", emailAddress);

        LOGGER.debug("Finding UserDetails by unique keys: {}", emailAddressMap);
        return this.wmGenericDao.findByUniqueKey(emailAddressMap);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class, value = "eshoppingTransactionManager")
    @Override
    public UserDetails update(UserDetails userDetails) {
        LOGGER.debug("Updating UserDetails with information: {}", userDetails);

        List<Orders> orderses = userDetails.getOrderses();
        CartDetails cartDetails = userDetails.getCartDetails();
        List<UserAddressDetails> userAddressDetailses = userDetails.getUserAddressDetailses();
        if(orderses != null && Hibernate.isInitialized(orderses)) {
            orderses.forEach(_orders -> _orders.setUserDetails(userDetails));
        }
        if(cartDetails != null && Hibernate.isInitialized(cartDetails)) {
            cartDetails.setUserDetails(userDetails);
        }
        if(userAddressDetailses != null && Hibernate.isInitialized(userAddressDetailses)) {
            userAddressDetailses.forEach(_userAddressDetails -> _userAddressDetails.setUserDetails(userDetails));
        }

        this.wmGenericDao.update(userDetails);
        this.wmGenericDao.refresh(userDetails);

        return userDetails;
    }

    @Transactional(value = "eshoppingTransactionManager")
    @Override
    public UserDetails delete(Integer userdetailsId) {
        LOGGER.debug("Deleting UserDetails with id: {}", userdetailsId);
        UserDetails deleted = this.wmGenericDao.findById(userdetailsId);
        if (deleted == null) {
            LOGGER.debug("No UserDetails found with id: {}", userdetailsId);
            throw new EntityNotFoundException(String.valueOf(userdetailsId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "eshoppingTransactionManager")
    @Override
    public void delete(UserDetails userDetails) {
        LOGGER.debug("Deleting UserDetails with {}", userDetails);
        this.wmGenericDao.delete(userDetails);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Page<UserDetails> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all UserDetails");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Page<UserDetails> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all UserDetails");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service eshopping for table UserDetails to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service eshopping for table UserDetails to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Page<Orders> findAssociatedOrderses(Integer userId, Pageable pageable) {
        LOGGER.debug("Fetching all associated orderses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("userDetails.userId = '" + userId + "'");

        return ordersService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "eshoppingTransactionManager")
    @Override
    public Page<UserAddressDetails> findAssociatedUserAddressDetailses(Integer userId, Pageable pageable) {
        LOGGER.debug("Fetching all associated userAddressDetailses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("userDetails.userId = '" + userId + "'");

        return userAddressDetailsService.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service UserAddressDetailsService instance
     */
    protected void setUserAddressDetailsService(UserAddressDetailsService service) {
        this.userAddressDetailsService = service;
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service OrdersService instance
     */
    protected void setOrdersService(OrdersService service) {
        this.ordersService = service;
    }

}