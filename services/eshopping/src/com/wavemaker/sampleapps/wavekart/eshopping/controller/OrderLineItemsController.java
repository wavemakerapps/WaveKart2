/*Copyright (c) 2015-2016 WaveMaker.com All Rights Reserved.
 This software is the confidential and proprietary information of WaveMaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with WaveMaker.com*/
package com.wavemaker.sampleapps.wavekart.eshopping.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.manager.ExportedFileManager;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.wavemaker.sampleapps.wavekart.eshopping.OrderLineItems;
import com.wavemaker.sampleapps.wavekart.eshopping.OrderLineItemsId;
import com.wavemaker.sampleapps.wavekart.eshopping.service.OrderLineItemsService;


/**
 * Controller object for domain model class OrderLineItems.
 * @see OrderLineItems
 */
@RestController("eshopping.OrderLineItemsController")
@Api(value = "OrderLineItemsController", description = "Exposes APIs to work with OrderLineItems resource.")
@RequestMapping("/eshopping/OrderLineItems")
public class OrderLineItemsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderLineItemsController.class);

    @Autowired
	@Qualifier("eshopping.OrderLineItemsService")
	private OrderLineItemsService orderLineItemsService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new OrderLineItems instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public OrderLineItems createOrderLineItems(@RequestBody OrderLineItems orderLineItems) {
		LOGGER.debug("Create OrderLineItems with information: {}" , orderLineItems);

		orderLineItems = orderLineItemsService.create(orderLineItems);
		LOGGER.debug("Created OrderLineItems with information: {}" , orderLineItems);

	    return orderLineItems;
	}

    @ApiOperation(value = "Returns the OrderLineItems instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public OrderLineItems getOrderLineItems(@RequestParam("orderId") Integer orderId, @RequestParam("productId") Integer productId) {

        OrderLineItemsId orderlineitemsId = new OrderLineItemsId();
        orderlineitemsId.setOrderId(orderId);
        orderlineitemsId.setProductId(productId);

        LOGGER.debug("Getting OrderLineItems with id: {}" , orderlineitemsId);
        OrderLineItems orderLineItems = orderLineItemsService.getById(orderlineitemsId);
        LOGGER.debug("OrderLineItems details with id: {}" , orderLineItems);

        return orderLineItems;
    }



    @ApiOperation(value = "Updates the OrderLineItems instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public OrderLineItems editOrderLineItems(@RequestParam("orderId") Integer orderId, @RequestParam("productId") Integer productId, @RequestBody OrderLineItems orderLineItems) {

        orderLineItems.setOrderId(orderId);
        orderLineItems.setProductId(productId);

        LOGGER.debug("OrderLineItems details with id is updated with: {}" , orderLineItems);

        return orderLineItemsService.update(orderLineItems);
    }


    @ApiOperation(value = "Deletes the OrderLineItems instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteOrderLineItems(@RequestParam("orderId") Integer orderId, @RequestParam("productId") Integer productId) {

        OrderLineItemsId orderlineitemsId = new OrderLineItemsId();
        orderlineitemsId.setOrderId(orderId);
        orderlineitemsId.setProductId(productId);

        LOGGER.debug("Deleting OrderLineItems with id: {}" , orderlineitemsId);
        OrderLineItems orderLineItems = orderLineItemsService.delete(orderlineitemsId);

        return orderLineItems != null;
    }


    /**
     * @deprecated Use {@link #findOrderLineItems(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of OrderLineItems instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<OrderLineItems> searchOrderLineItemsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering OrderLineItems list by query filter:{}", (Object) queryFilters);
        return orderLineItemsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of OrderLineItems instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<OrderLineItems> findOrderLineItems(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering OrderLineItems list by filter:", query);
        return orderLineItemsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of OrderLineItems instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<OrderLineItems> filterOrderLineItems(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering OrderLineItems list by filter", query);
        return orderLineItemsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportOrderLineItems(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return orderLineItemsService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportOrderLineItemsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = OrderLineItems.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> orderLineItemsService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of OrderLineItems instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countOrderLineItems( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting OrderLineItems");
		return orderLineItemsService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getOrderLineItemsAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return orderLineItemsService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service OrderLineItemsService instance
	 */
	protected void setOrderLineItemsService(OrderLineItemsService service) {
		this.orderLineItemsService = service;
	}

}