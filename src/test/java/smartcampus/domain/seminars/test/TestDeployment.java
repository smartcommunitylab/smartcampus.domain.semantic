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

import it.sayservice.platform.client.DomainEngineClient;
import it.sayservice.platform.client.DomainObject;
import it.sayservice.platform.client.ServiceBusClient;
import it.sayservice.platform.client.jms.JMSDomainEngineClient;
import it.sayservice.platform.client.jms.JMSServiceBusClient;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.jms.client.HornetQJMSConnectionFactory;


public class TestDeployment {

	public static void main(String[] args) throws Exception {
		HornetQJMSConnectionFactory cf = 
			     new HornetQJMSConnectionFactory(false,
			                  new TransportConfiguration(
			                    "org.hornetq.core.remoting.impl.netty.NettyConnectorFactory"));
			  ServiceBusClient client = new JMSServiceBusClient(cf);
			  DomainEngineClient doClient = new JMSDomainEngineClient(cf);

			  List<String> list = doClient.searchDomainObjects("eu.trentorise.smartcampus.domain.discovertrento.EventObject", null);
			  DomainObject obj = new DomainObject(list.get(0));
			  System.err.println(obj);
			  Map<String,Object> parameters = new HashMap<String, Object>();
			  parameters.put("newTags", "[{\"id\":1,\"name\":\"tag1\",\"description\":\"descr1\"}]");
			  doClient.invokeDomainOperation("updateTags", obj.getType(), obj.getId(), parameters, null, null);

			  parameters.clear();
			  parameters.put("newId", "501b712bc73952a398758c7b");
			  doClient.invokeDomainOperation("updatePOI", obj.getType(), obj.getId(), parameters, null, null);
			  
			  obj = new DomainObject(doClient.searchDomainObject(obj.getType(), obj.getId(), null));
			  System.err.println(obj);
	}
	
	public static class TagData implements Serializable {
		private static final long serialVersionUID = 186582880925701476L;
		private Long id;
		private String name;
		private String description;
		private String summary;

		public TagData(Long id, String name, String description, String summary) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.summary = summary;
		}
		public TagData() {
			super();
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}
	}
}
