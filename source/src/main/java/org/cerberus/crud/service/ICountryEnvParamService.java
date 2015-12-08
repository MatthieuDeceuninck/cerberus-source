/*
 * Cerberus  Copyright (C) 2013  vertigo17
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This file is part of Cerberus.
 *
 * Cerberus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Cerberus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cerberus.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.cerberus.crud.service;

import java.util.List;
import org.cerberus.crud.entity.CountryEnvParam;
import org.cerberus.exception.CerberusException;
import org.cerberus.util.answer.Answer;
import org.cerberus.util.answer.AnswerItem;
import org.cerberus.util.answer.AnswerList;
import org.json.JSONObject;

/**
 *
 * @author bcivel
 */
public interface ICountryEnvParamService {

    CountryEnvParam findCountryEnvParamByKey(String system, String country, String environment) throws CerberusException;

    List<CountryEnvParam> findCountryEnvParamByCriteria(CountryEnvParam countryEnvParam) throws CerberusException;

    List<JSONObject> findActiveEnvironmentBySystemCountryApplication(String system, String country, String application) throws CerberusException;

    /**
     * Find all countryEnvParam by System
     *
     * @param system
     * @return
     * @throws CerberusException
     */
    List<CountryEnvParam> findAll(String system) throws CerberusException;

    /**
     * Update countryEnvParam
     *
     * @param cep
     * @throws CerberusException
     */
    void update_deprecated(CountryEnvParam cep) throws CerberusException;

    /**
     * Delete countryEnvParam
     *
     * @param cep
     * @throws CerberusException
     */
    void delete_deprecated(CountryEnvParam cep) throws CerberusException;

    /**
     * Create countryEnvParam
     *
     * @param cep
     * @throws CerberusException
     */
    void create_deprecated(CountryEnvParam cep) throws CerberusException;

    /**
     * Find List of CountryEnvParam by Criteria
     *
     * @param start row number of the resulset where start the List
     * (limit(start,amount))
     * @param amount number of row returned
     * @param column column used for the sort (sort by column dir >become> sort
     * by country asc)
     * @param dir asc or desc
     * @param searchTerm
     * @param individualSearch
     * @return
     */
    public List<CountryEnvParam> findListByCriteria(String system, int start, int amount, String column, String dir, String searchTerm, String individualSearch);

    /**
     * Find the number of CountryEnvParam found respecting the search criteria
     *
     * @param searchTerm
     * @return
     */
    public Integer count(String searchTerm, String inds);

    public List<CountryEnvParam> findListByCriteria(String system);

    /**
     *
     * @param system
     * @param country
     * @param environment
     * @return
     */
    AnswerItem readByKey(String system, String country, String environment);

    /**
     *
     * @param system
     * @return
     */
    public AnswerList readActiveBySystem(String system);

    /**
     *
     * @param startPosition
     * @param length
     * @param columnName
     * @param sort
     * @param searchParameter
     * @param string
     * @return
     */
    public AnswerList readByCriteria(int startPosition, int length, String columnName, String sort, String searchParameter, String string);

    /**
     *
     * @param system
     * @param country
     * @param environment
     * @param build
     * @param revision
     * @param Active
     * @param startPosition
     * @param length
     * @param columnName
     * @param sort
     * @param searchParameter
     * @param string
     * @return
     */
    public AnswerList readByVariousByCriteria(String system, String country, String environment, String build, String revision, String Active, int startPosition, int length, String columnName, String sort, String searchParameter, String string);

    /**
     *
     * @param system
     * @param country
     * @param environment
     * @return true is application exist or false is application does not exist
     * in database.
     */
    boolean exist(String system, String country, String environment);

    /**
     *
     * @param cep
     * @return
     */
    Answer create(CountryEnvParam cep);

    /**
     *
     * @param cep
     * @return
     */
    Answer delete(CountryEnvParam cep);

    /**
     *
     * @param cep
     * @return
     */
    Answer update(CountryEnvParam cep);

    /**
     *
     * @param answerItem
     * @return
     * @throws CerberusException
     */
    CountryEnvParam convert(AnswerItem answerItem) throws CerberusException;

    /**
     *
     * @param answerList
     * @return
     * @throws CerberusException
     */
    List<CountryEnvParam> convert(AnswerList answerList) throws CerberusException;

    /**
     *
     * @param answer
     * @throws CerberusException
     */
    void convert(Answer answer) throws CerberusException;

}
