/*******************************************************************************
 * Copyright 2012-2013 Trento RISE
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 ******************************************************************************/
package smartcampus.domain.seminars.test;

import it.sayservice.platform.client.ServiceBusClient;
import it.sayservice.platform.client.jms.JMSServiceBusClient;
import it.sayservice.platform.core.message.Core.DODataRequest;
import it.sayservice.platform.core.message.Core.DomainEvent;
import it.sayservice.platform.domain.test.DomainListener;
import it.sayservice.platform.domain.test.DomainTestHelper;

import java.util.HashMap;
import java.util.List;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.jms.client.HornetQJMSConnectionFactory;

import eu.trentorise.smartcampus.domain.semantic.SemanticServiceDOEngine;
import eu.trentorise.smartcampus.domain.semantic.Tag;


public class TestDomain {

	public static void main(String[] args) throws Exception {
		HornetQJMSConnectionFactory cf = 
			     new HornetQJMSConnectionFactory(false,
			                  new TransportConfiguration(
			                    "org.hornetq.core.remoting.impl.netty.NettyConnectorFactory"));
			  ServiceBusClient client = new JMSServiceBusClient(cf);

			  DomainTestHelper helper = new DomainTestHelper(client,new DomainListener() {
			    public void onDomainEvents(List<DomainEvent> events) {
			      // DO someth...
			    }

			    public void onDataRequest(DODataRequest req) {
			      // DO someth...
			    }
			  });
			  helper.cleanDomainData();

			  helper.start(new SemanticServiceDOEngine());
			  HashMap<String, Object> params = new HashMap<String, Object>();
			  params.put("type", "event");
			  params.put("name", "test event");
			  params.put("description", "test description");
			  Tag textTag = new Tag();
			  textTag.setName("textTag");
			  Tag semanticTag = new Tag();
			  semanticTag.setId(54824L);
			  semanticTag.setName("java");
			  params.put("tags", new Tag[]{textTag, semanticTag});
			  
			  Long result = (Long)helper.invokeDOOperationSync("eu.trentorise.smartcampus.domain.semantic.SemanticService", "eu.trentorise.smartcampus.domain.semantic.SemanticService.0", "createSCEntity", params);
			  System.err.println(result);
			  
			  params.clear();
			  params.put("id", result);
			  Boolean deleteResult = (Boolean)helper.invokeDOOperationSync("eu.trentorise.smartcampus.domain.semantic.SemanticService", "eu.trentorise.smartcampus.domain.semantic.SemanticService.0", "deleteEntity", params);
			  System.err.println(deleteResult);
			  
	}
}
