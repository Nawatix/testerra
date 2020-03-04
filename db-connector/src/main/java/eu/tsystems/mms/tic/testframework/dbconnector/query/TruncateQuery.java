/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Peter Lehmann
 *     pele
*/
package eu.tsystems.mms.tic.testframework.dbconnector.query;

import eu.tsystems.mms.tic.testframework.dbconnector.Table;

/**
 * Class representing a truncate query.
 *
 * @param <T>
 *            Table to truncate.
 *
 * @author sepr
 *
 */
public class TruncateQuery<T extends Table> extends Query<T> implements INonSelectQuery {

    /**
     * Creates a query to truncate the specified table.
     *
     * @param table
     *            Table to truncate.
     */
    public TruncateQuery(final T table) {
        this.setFromTable(table);
        setQueryString(String.format("TRUNCATE TABLE %s", table.getTableName()));
    }

}
