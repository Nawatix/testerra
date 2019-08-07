/*
 * (C) Copyright T-Systems Multimedia Solutions GmbH 2018, ..
 *
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
 *     Peter Lehmann <p.lehmann@t-systems.com>
 *     pele <p.lehmann@t-systems.com>
 */
package eu.tsystems.mms.tic.testframework.events;

/**
 * Created with IntelliJ IDEA.
 * User: peter
 * Date: 14.10.13
 * Time: 23:10
 * To change this template use File | Settings | File Templates.
 */
public enum TesterraEventType implements ITesterraEventType {

    TEST_METHOD_START,
    TEST_METHOD_END,

    CONFIGURATION_METHOD_START,
    CONFIGURATION_METHOD_END,

    TEST_START,
    TEST_END,

    RETRYING_METHOD,

    FIRST_FAILED_TEST,
    TEST_WITH_FILTERED_THROWABLE,

    SYNC_METHOD_RESULT,

    TAKE_SCREENSHOTS,

    CONTEXT_UPDATE

}