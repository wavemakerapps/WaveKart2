/*Copyright (c) 2015-2016 WaveMaker.com All Rights Reserved.
 This software is the confidential and proprietary information of WaveMaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with WaveMaker.com*/
package com.wavemaker.sampleapps.wavekart.eshopping.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.wavemaker.sampleapps.wavekart.eshopping.CartDetails;
import com.wavemaker.sampleapps.wavekart.eshopping.CartItems;

/**
 * Service object for domain model class {@link CartDetails}.
 */
public interface CartDetailsService {

    /**
     * Creates a new CartDetails. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on CartDetails if any.
     *
     * @param cartDetails Details of the CartDetails to be created; value cannot be null.
     * @return The newly created CartDetails.
     */
    CartDetails create(@Valid CartDetails cartDetails);


	/**
     * Returns CartDetails by given id if exists.
     *
     * @param cartdetailsId The id of the CartDetails to get; value cannot be null.
     * @return CartDetails associated with the given cartdetailsId.
	 * @throws EntityNotFoundException If no CartDetails is found.
     */
    CartDetails getById(Integer cartdetailsId);

    /**
     * Find and return the CartDetails by given id if exists, returns null otherwise.
     *
     * @param cartdetailsId The id of the CartDetails to get; value cannot be null.
     * @return CartDetails associated with the given cartdetailsId.
     */
    CartDetails findById(Integer cartdetailsId);

	/**
     * Find and return the list of CartDetails by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param cartdetailsIds The id's of the CartDetails to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return CartDetails associated with the given cartdetailsIds.
     */
    List<CartDetails> findByMultipleIds(List<Integer> cartdetailsIds, boolean orderedReturn);

    /**
     * Find and return the CartDetails for given userId  if exists.
     *
     * @param userId value of userId; value cannot be null.
     * @return CartDetails associated with the given inputs.
     * @throws EntityNotFoundException if no matching CartDetails found.
     */
    CartDetails getByUserId(int userId);

    /**
     * Updates the details of an existing CartDetails. It replaces all fields of the existing CartDetails with the given cartDetails.
     *
     * This method overrides the input field values using Server side or database managed properties defined on CartDetails if any.
     *
     * @param cartDetails The details of the CartDetails to be updated; value cannot be null.
     * @return The updated CartDetails.
     * @throws EntityNotFoundException if no CartDetails is found with given input.
     */
    CartDetails update(@Valid CartDetails cartDetails);

    /**
     * Deletes an existing CartDetails with the given id.
     *
     * @param cartdetailsId The id of the CartDetails to be deleted; value cannot be null.
     * @return The deleted CartDetails.
     * @throws EntityNotFoundException if no CartDetails found with the given id.
     */
    CartDetails delete(Integer cartdetailsId);

    /**
     * Deletes an existing CartDetails with the given object.
     *
     * @param cartDetails The instance of the CartDetails to be deleted; value cannot be null.
     */
    void delete(CartDetails cartDetails);

    /**
     * Find all CartDetails matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching CartDetails.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<CartDetails> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all CartDetails matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching CartDetails.
     *
     * @see Pageable
     * @see Page
     */
    Page<CartDetails> findAll(String query, Pageable pageable);

    /**
     * Exports all CartDetails matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
     */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

    /**
     * Exports all CartDetails matching the given input query to the given exportType format.
     *
     * @param options The export options provided by the user; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @param outputStream The output stream of the file for the exported data to be written to.
     *
     * @see DataExportOptions
     * @see Pageable
     * @see OutputStream
     */
    void export(DataExportOptions options, Pageable pageable, OutputStream outputStream);

    /**
     * Retrieve the count of the CartDetails in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the CartDetails.
     */
    long count(String query);

    /**
     * Retrieve aggregated values with matching aggregation info.
     *
     * @param aggregationInfo info related to aggregations.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return Paginated data with included fields.
     *
     * @see AggregationInfo
     * @see Pageable
     * @see Page
	 */
    Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable);

    /*
     * Returns the associated cartItemses for given CartDetails id.
     *
     * @param cartId value of cartId; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated CartItems instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<CartItems> findAssociatedCartItemses(Integer cartId, Pageable pageable);

}