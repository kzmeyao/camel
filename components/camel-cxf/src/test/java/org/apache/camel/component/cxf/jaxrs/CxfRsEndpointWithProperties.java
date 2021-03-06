/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.cxf.jaxrs;

import org.apache.camel.CamelContext;
import org.apache.camel.component.cxf.spring.AbstractSpringBeanTestSupport;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

public class CxfRsEndpointWithProperties extends AbstractSpringBeanTestSupport {

    @Override
    protected String[] getApplicationContextFiles() {
        return new String[] {"org/apache/camel/component/cxf/jaxrs/CxfRsEndpointWithProperties.xml"};
    }

    @Test
    public void testCxfRsBeanWithCamelPropertiesHolder() throws Exception {
        // get the camelContext from application context
        CamelContext camelContext = ctx.getBean("camel", CamelContext.class);
        CxfRsEndpoint testEndpoint = camelContext.getEndpoint("cxfrs:bean:testEndpoint", CxfRsEndpoint.class);
        assertEquals("Got a wrong address", "http://localhost:9900/testEndpoint", testEndpoint.getAddress());
        HttpGet get = new HttpGet(testEndpoint.getAddress());
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(get);
        assertEquals(404, response.getStatusLine().getStatusCode());
    }

}
